package com.archilabs.pica.bolivariano;

import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import com.archilabs.pica.bolivariano.ftp.FtpService;
import com.archilabs.pica.bolivariano.model.Trip;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TripsService {

    private final FtpService ftpService;

    public TripsService(FtpService ftpService) {
        this.ftpService = ftpService;
    }

    public void connectToFtp(String host, String user, String pass) throws FtpException {
        ftpService.connectToFTP(host, user, pass);
    }

    public void retrieveTripsForTheDay(String ftpRelativePath, String copyToPath) throws FtpException {
        ftpService.downloadFileFromFTP(ftpRelativePath, copyToPath);
    }

    public List<Trip> getTrips(String departureCity, String arrivalCity, String time) throws TripsFileNotFoundException {
        List<Trip> trips = new ArrayList<>();

        try {
            File inputFile = new File(Paths.get("").toAbsolutePath().toString() + "\\availableTrips.csv");

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
            throw new TripsFileNotFoundException("Trips file not found, make sure to connect to Bolivariano and retrieve the latest version");
        } catch (IOException e) {
            e.printStackTrace();
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
}
