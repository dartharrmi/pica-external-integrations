package com.archilabs.pica.bolivariano.ftp;

import com.archilabs.pica.bolivariano.exceptions.FtpException;

import java.io.File;

public interface IFtpService {

    void connectToFTP(String host, String user, String pass) throws FtpException;

    void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws FtpException;

    void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws FtpException;

    void disconnectFTP() throws FtpException;
}
