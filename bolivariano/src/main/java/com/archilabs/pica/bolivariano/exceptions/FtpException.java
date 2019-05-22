package com.archilabs.pica.bolivariano.exceptions;

public class FtpException extends Exception {

    private FtpError ftpError;

    public FtpException(FtpError ftpError) {
        super(ftpError.getErrormessage());
    }

    public FtpError getFtpError() {
        return ftpError;
    }
}
