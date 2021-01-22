package com.englishapp.demoen.service.fileManagerService;


import com.amazonaws.services.s3.model.Bucket;
import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.FileDataInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FolderOperationService {

    FileDataInfo uploadFile(MultipartFile multipartFile, String fileName, int startNewAudioLoadTrough, String sentence, String fileFolder);
    void deleteFolderOperation(String folderName,String fileName);
    BucketFolder createFolder(MultipartFile imageFolderFile, String imageFileName, String folderName, String description);
    void setFavBucketFolder( Long bucketFolderName);
    void deleteFavBucketFolder(Long bucketFolderId);
}
