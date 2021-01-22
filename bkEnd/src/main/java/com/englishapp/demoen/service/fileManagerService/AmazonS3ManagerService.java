package com.englishapp.demoen.service.fileManagerService;

import com.amazonaws.services.s3.AmazonS3;

import java.io.File;
import java.util.List;

public interface AmazonS3ManagerService {

    void createFolder (String folderName);
    void deleteFolder (String folderName,String fileName);
    void deleteFile(String folderName, String fileName);
    void uploadFileTos3bucket(String fileName, File file,String bucketName,String folderName,AmazonS3 amazonS3);
    List<String> getAllFolders();
}
