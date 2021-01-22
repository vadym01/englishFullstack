package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.englishapp.demoen.service.fileManagerService.AmazonS3ManagerService;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import static com.englishapp.demoen.InfoProvider.*;

@Service
public class AmazonS3ManagerServiceImpl implements AmazonS3ManagerService {

    @Autowired
    private AmazonS3 amazonS3;



    @Autowired
    private BucketFolderService bucketFolderService;

    @Override
    public void createFolder(String folderName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, folderName + SUFFIX,emptyContent, metadata);
        amazonS3.putObject(putObjectRequest);

    }

    @Override
    public void deleteFolder(String folderName,String fileName) {

            List<S3ObjectSummary> fileList = amazonS3.listObjects(BUCKET_NAME,folderName).getObjectSummaries();

            for (S3ObjectSummary file : fileList) {
                amazonS3.deleteObject(BUCKET_NAME, file.getKey());
            }
        amazonS3.deleteObject(BUCKET_FOR_IMAGES_NAME +SUFFIX + FOLDER_FROM_BUCKET_IMAGES_NAME, fileName);
            amazonS3.deleteObject(BUCKET_NAME, folderName);

        }

    @Override
    public void deleteFile(String folderName,String fileName) {
        amazonS3.deleteObject(BUCKET_NAME +SUFFIX + folderName, fileName);
    }


    @Override
    public void uploadFileTos3bucket(String fileName, File file,String bucketName,String folderName,AmazonS3 amazonS3) {
        amazonS3.putObject(new PutObjectRequest(bucketName + SUFFIX + folderName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public List<String> getAllFolders() {
        return null;
    }


}
