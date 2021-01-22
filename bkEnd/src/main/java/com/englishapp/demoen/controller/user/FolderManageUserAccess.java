package com.englishapp.demoen.controller.user;

import com.englishapp.demoen.entity.dto.BucketFolderSelectWithFavoriteDTO;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class FolderManageUserAccess {

    @Autowired
    private BucketFolderService bucketFolderService;

    @GetMapping("/folderManage")
    public List<BucketFolderSelectWithFavoriteDTO> getAllFoldersFormS3(){
        return bucketFolderService.getAllFoldersWithFavoriteSelected();
    }
}
