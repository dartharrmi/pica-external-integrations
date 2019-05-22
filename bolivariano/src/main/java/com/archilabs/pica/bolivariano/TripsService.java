package com.archilabs.pica.bolivariano;

import com.archilabs.pica.bolivariano.exceptions.BookingTripException;
import com.archilabs.pica.bolivariano.exceptions.ConnectionNotClosedException;
import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import com.archilabs.pica.bolivariano.ftp.FtpService;
import com.archilabs.pica.bolivariano.model.Trip;
import com.archilabs.pica.bolivariano.model.request.BookingRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TripsService {

    /**
     * By default, all trips are non confirmed until confirmed by Bolivarianos's staff.
     */
    private final String orderHeader = "APELLIDO,NOMBRE,FECHA_SALIDA(DDMMYYYY),NUM_VIAJE,NUM_SILLA,NA";

    private final FtpService ftpService;
    private final String fileName;

    public TripsService(FtpService ftpService) {
        this.ftpService = ftpService;

        String date = new SimpleDateFormat("ddMMYYYY").format(new Date());
        fileName = "viajes" + date + ".csv";
    }

    public void connectToFtp(String host, String user, String pass) throws FtpException {
        ftpService.connectToFTP(host, user, pass);
    }

    public void retrieveTripsForTheDay(String ftpRelativePath, String copyToPath) throws FtpException {
        ftpService.downloadFileFromFTP(ftpRelativePath, copyToPath);
    }

    public List<Trip> getTrips(String departureCity, String arrivalCity, String time) throws TripsFileNotFoundException, ConnectionNotClosedException {
        List<Trip> trips = new ArrayList<>();

        try {
            File inputFile = new File(Paths.get("").toAbsolutePath().toString() + "\\" + fileName);

            InputStream inputStream = new FileInputStream(inputFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            trips = reader
                    .lines()
                    .skip(1)
                    .map(mapLineToTrip)
                    .filter(trip -> trip.getDepartureCity().equalsIgnoreCase(departureCity))
                    .filter(trip -> trip.getArrivalCity().equalsIgnoreCase(arrivalCity))
                    .filter(trip -> trip.getDepartureTime().substring(0, 2).equals(time.substring(0, 2)))
                    .sorted(Comparator.comparing(Trip::getDepartureTime))
                    .collect(Collectors.toList());

            reader.close();
        } catch (FileNotFoundException e) {
            throw new TripsFileNotFoundException("Error retrieving the trips from Bolivariano, please try again later");
        } catch (IOException e) {
            throw new ConnectionNotClosedException("Error stablishing the communication with Bolivariano, please try again later");
        }

        return trips;
    }

    private Function<String, Trip> mapLineToTrip = (line) -> {
        String[] p = line.split(",");
        Trip trip = new Trip();

        trip.setTripNumber(p[0]);
        trip.setDepartureCity(p[1]);
        trip.setArrivalCity(p[2]);
        trip.setSeatNumber(p[3]);
        trip.setDepartureTime(p[4]);

        return trip;
    };

    public void bookTrip(BookingRequest request) throws BookingTripException, FtpException {
        String orderMillisCreation = String.valueOf(System.currentTimeMillis());
        String orderId = orderMillisCreation.substring(orderMillisCreation.length() - 4);

        try {
            File tempFile = new File(System.getProperty("java.io.tmpdir") + "\\touresbalon_orden" + orderId + ".csv");

            FileWriter writer = new FileWriter(tempFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.write(orderHeader + "\n");
            printWriter.write(request.getLastName() + "," + request.getName() + "," + request.getDepartureDate() + "," + request.getTripNumber() + "," + request.getSeatNumber() + ",NA");

            printWriter.close();
            bufferedWriter.close();
            writer.close();

            ftpService.uploadFileToFTP(tempFile, "orders", tempFile.getName());
        } catch (IOException e) {
            throw new BookingTripException("An error ocurred booking the trip, please try again later");
        } catch (FtpException e) {
            throw e;
        }
    }
}
