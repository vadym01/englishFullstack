package com.englishapp.demoen.service.fileManagerService;

import com.amazonaws.services.s3.model.Bucket;
import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.entity.dto.BucketFolderSelectWithFavoriteDTO;

import java.util.List;
import java.util.Set;

public interface BucketFolderService {

    void deleteFolder(String folderName);
    BucketFolder saveNewFolder(BucketFolder bucketFolder);
    List<BucketFolder> getAllFolders();
    List<BucketFolderSelectWithFavoriteDTO> getAllFoldersWithFavoriteSelected();
    BucketFolder findBucketFolderById(Long id);


}
