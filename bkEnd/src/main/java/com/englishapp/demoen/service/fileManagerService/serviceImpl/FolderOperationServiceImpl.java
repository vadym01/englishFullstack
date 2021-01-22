package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.repository.UserRepository;
import com.englishapp.demoen.service.fileManagerService.*;
import com.englishapp.demoen.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.englishapp.demoen.InfoProvider.*;

@Service
public class FolderOperationServiceImpl implements FolderOperationService {


    @Autowired
    private FileOperationService fileOperationService;

    @Autowired
    private FileDataInfoService fileDataInfoService;

    @Autowired
    private AmazonS3ManagerService amazonS3ManagerService;

    @Autowired
    private BucketFolderService bucketFolderService;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Autowired
    private UserService userService;

    @Override
    public FileDataInfo uploadFile(MultipartFile multipartFile, String fileName, int startNewAudioLoadTrough, String sentence, String fileFolder) {
        String customFileName = fileOperationService.fileNameGenerator(fileName);
        String fileUrl =  ENDPOINT_URL  + SUFFIX + fileFolder + SUFFIX +  customFileName;
        File file = null;
        try {
            file = fileOperationService.multipartToFileConverter(multipartFile);
        } catch (IOException e) {
            e.getStackTrace();
        }
        amazonS3ManagerService.uploadFileTos3bucket (customFileName,file,BUCKET_NAME,fileFolder,amazonS3);

         FileDataInfo fileDataInfo = fileDataInfoService.saveNewFileData(customFileName,multipartFile.getContentType(),
                multipartFile.getSize(),fileUrl,fileFolder,sentence,startNewAudioLoadTrough);
        file.delete();
        return fileDataInfo;
    }


    @Override
    public BucketFolder createFolder(MultipartFile imageFolderFile, String imageFileName, String folderName, String description) {
        String customFileName = fileOperationService.fileNameGenerator(imageFileName);
        String fileUrl =  ENDPOINT_IMAGE_FOLDERS_URL  + SUFFIX + "images-for-folders" + SUFFIX +  customFileName;
        File file = null;
        try {
            file = fileOperationService.multipartToFileConverter(imageFolderFile);
        } catch (IOException e) {
            e.getStackTrace();
        }
        amazonS3ManagerService.uploadFileTos3bucket (customFileName,file,BUCKET_FOR_IMAGES_NAME,"images-for-folders",amazonS3);

        BucketFolder bucketFolder = new BucketFolder(folderName,description,fileUrl,imageFileName);

        amazonS3ManagerService.createFolder(bucketFolder.getFolderName());
        file.delete();
        return bucketFolderService.saveNewFolder(bucketFolder);
    }





//    @Override
//    public BucketFolder createFolder(BucketFolder bucketFolder) {
//        amazonS3ManagerService.createFolder(bucketFolder.getFolderName());
//        return bucketFolderService.saveNewFolder(bucketFolder);
//    }

    @Override
    public void deleteFolderOperation(String folderName,String fileName) {
        amazonS3ManagerService.deleteFolder(folderName, fileName);
        bucketFolderService.deleteFolder(folderName);
    }


    @Override
    public void setFavBucketFolder(Long bucketFolderId) {
        User user = userService.getUserObj();
        BucketFolder bucketFolder = bucketFolderRepository.findById(bucketFolderId).get();

        Set<User> userSet = bucketFolder.getUser();
        Set<BucketFolder> bucketFolderSet = user.getFvBucketFolder();

        userSet.add(user);
        bucketFolderSet.add(bucketFolder);


        bucketFolder.setUser(userSet);
        user.setFvBucketFolder(bucketFolderSet);
        userRepository.save(user);

    }



    @Override
    public void deleteFavBucketFolder(Long bucketFolderId) {
        User user = userService.getUserObj();
        BucketFolder bucketFolder = bucketFolderRepository.findById(bucketFolderId).get();

        Set<User> userSet = bucketFolder.getUser();
        Set<BucketFolder> bucketFolderSet = user.getFvBucketFolder();

        userSet.remove(user);
        bucketFolderSet.remove(bucketFolder);


        bucketFolder.setUser(userSet);
        user.setFvBucketFolder(bucketFolderSet);
        userRepository.save(user);

    }
}
