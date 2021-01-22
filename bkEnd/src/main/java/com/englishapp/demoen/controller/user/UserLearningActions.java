package com.englishapp.demoen.controller.user;

import com.englishapp.demoen.entity.UserPageInfo;
import com.englishapp.demoen.entity.dto.UserPageInfoPostDto;
import com.englishapp.demoen.entity.dto.userPageInfoDtoRequest.UserPageInfoDto;
import com.englishapp.demoen.service.fileManagerService.FolderOperationService;
import com.englishapp.demoen.service.userService.UserPageInfoService;
import com.englishapp.demoen.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserLearningActions {

    @Autowired
    private UserPageInfoService userPageInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private FolderOperationService folderOperationService;
    
    @PostMapping("/{fileDataInfoId}/{userId}")
    public UserPageInfo userPageInfo(@PathVariable Long fileDataInfoId,@PathVariable Long userId){

        return userPageInfoService.setUserPageInfo(fileDataInfoId,userId);
    }

    @PostMapping("/markFavFolder/{bucketFolderId}")
    public void setFavBucketFolder(@PathVariable Long bucketFolderId){
        folderOperationService.setFavBucketFolder(bucketFolderId);
    }

    @DeleteMapping("/markFavFolder/{bucketFolderId}")
    public void deleteFavBucketFolder(@PathVariable Long bucketFolderId){
        folderOperationService.deleteFavBucketFolder(bucketFolderId);
    }

    @PostMapping("/saveToUserRepeatCollection")
    public ResponseEntity saveToUserRepeatCollection(@RequestBody List<UserPageInfoPostDto> userPageInfoPostDtoList){
//        userPageInfoPostDtoList.forEach(System.out::println);
        userPageInfoService.saveListToUserRepeatCollection(userPageInfoPostDtoList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getRepeatCollection")
    public ResponseEntity getRepeatCollection(){
        List<UserPageInfoDto> userRepeatCollection =  userPageInfoService.getRepeatCollection();
        return new ResponseEntity(userRepeatCollection,HttpStatus.OK);
    }

    @PatchMapping("/setFavLearningUnit/{id}")
    public ResponseEntity<?> setFavLearningUnit(@PathVariable Long id){
        UserPageInfo userPageInfo = userPageInfoService.setFavUserPageInfoUnit(id);
        return new ResponseEntity<>(userPageInfo,HttpStatus.OK);
    }

    @DeleteMapping("/deleteFromLearningList/{id}")
    public ResponseEntity<?> deleteFromLearningList(@PathVariable Long id){
        userPageInfoService.deleteFromLearningList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
