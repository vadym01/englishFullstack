package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.service.fileManagerService.AmazonS3ManagerService;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import com.englishapp.demoen.service.fileManagerService.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileOperationServiceImpl implements FileOperationService {



    @Autowired
    private FileDataInfoService fileDataInfoService;

    @Autowired
    private AmazonS3ManagerService amazonS3ManagerService;

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Override
    public File multipartToFileConverter(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

    @Override
    public String fileNameGenerator(String fileName) {
        return fileName.replace(" ","_");
    }

    @Override
    public void deleteFile(String folderName, String fileName) {
        BucketFolder bucketFolder = bucketFolderRepository.findByFolderName(folderName);
        fileDataInfoService.deleteFile(bucketFolder,fileName);
        amazonS3ManagerService.deleteFile(folderName,fileName);

    }
}
