package com.englishapp.demoen.exceptions;

public class FileNameAlreadyExistResponse {

    private String fileName;

    public FileNameAlreadyExistResponse(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
