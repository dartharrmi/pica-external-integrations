package com.archilabs.pica.bolivariano.ftp;

import com.archilabs.pica.bolivariano.exceptions.ftp.FtpError;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FtpService implements IFtpService {

    /**
     * FTP connection handler
     */
    private FTPClient ftpconnection;

    /**
     * Method that implement FTP connection.
     *
     * @param host IP of FTP server.
     * @param user FTP valid user.
     * @param pass FTP valid pass for user.
     * @throws FtpException Set of possible errors associated with connection process.
     */
    @Override
    public void connectToFTP(String host, String user, String pass) throws FtpException {

        ftpconnection = new FTPClient();
        ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;

        try {
            ftpconnection.connect(host);
        } catch (IOException e) {
            FtpError errorMessage = new FtpError(-1, "Error connecting to FTP with host: " + host);
            throw new FtpException(errorMessage);
        }

        reply = ftpconnection.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {

            try {
                ftpconnection.disconnect();
            } catch (IOException e) {
                FtpError errorMessage = new FtpError(-2, "Error connecting to FTP with host: " + host + ". Response: " + reply);
                throw new FtpException(errorMessage);
            }
        }

        try {
            ftpconnection.login(user, pass);
        } catch (IOException e) {
            FtpError errorMessage = new FtpError(-3, "User: " + user + " or password invalid");
            throw new FtpException(errorMessage);
        }

        try {
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            FtpError errorMessage = new FtpError(-4, "Datatype not supported");
            throw new FtpException(errorMessage);
        }

        ftpconnection.enterLocalPassiveMode();
    }

    /**
     * Method that allow upload file to FTP
     *
     * @param file           File object of file to upload.
     * @param ftpHostDir     FTP host internal directory to save file.
     * @param serverFilename Name to put the file in FTP server.
     * @throws FtpException Set of possible errors associated with upload process.
     */
    @Override
    public void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws FtpException {

        try {
            InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            FtpError errorMessage = new FtpError(-5, "Error uploading the file");
            throw new FtpException(errorMessage);
        }

    }

    /**
     * Method for download files from FTP.
     *
     * @param ftpRelativePath Relative path of file to download into FTP server.
     * @param copytoPath      Path to copy the file in download process.
     * @throws FtpException Set of errors associated with download process.
     */

    @Override
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws FtpException {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            FtpError errorMessage = new FtpError(-6, "Error accessing the folder, check the permission granted to the user");
            throw new FtpException(errorMessage);
        }

        try {
            this.ftpconnection.retrieveFile(ftpRelativePath, fos);
        } catch (IOException e) {
            FtpError errorMessage = new FtpError(-7, "Error downloading the file");
            throw new FtpException(errorMessage);
        }
    }

    /**
     * Method for release the FTP connection.
     *
     * @throws FtpException Error if unplugged process failed.
     */
    @Override
    public void disconnectFTP() throws FtpException {
        if (this.ftpconnection.isConnected()) {
            try {
                this.ftpconnection.logout();
                this.ftpconnection.disconnect();
            } catch (IOException f) {
                throw new FtpException(new FtpError(-8, "Error disconnecting."));
            }
        }
    }
}
