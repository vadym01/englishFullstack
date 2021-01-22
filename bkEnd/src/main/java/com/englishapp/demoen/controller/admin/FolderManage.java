package com.englishapp.demoen.controller.admin;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.dto.BucketFolderSelectWithFavoriteDTO;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import com.englishapp.demoen.service.fileManagerService.FolderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class FolderManage {

    @Autowired
    private FolderOperationService folderOperationService;

    @Autowired
    private BucketFolderService bucketFolderService;

//    @PostMapping("/folderManage")
//    public BucketFolder createFolder(@RequestBody BucketFolder bucketFolder){
//        return folderOperationService.createFolder(bucketFolder);
////        return bucketFolderService.getAllFolders();
//    }

    @PostMapping("/folderManage")
    public BucketFolder createFolder(@RequestParam("imageFolderFile") MultipartFile imageFolderFile,
                                     @RequestParam("imageFileName") String imageFileName,
                                     @RequestParam("folderName") String folderName,
                                     @RequestParam("description") String description
                                     ){

        return folderOperationService.createFolder(imageFolderFile, imageFileName, folderName, description);
//        return bucketFolderService.getAllFolders();
    }


    @DeleteMapping("/folderManage/{folderName}/{imageFileName}")
    public ResponseEntity<String> deleteFolder(@PathVariable("folderName") String folderName,@PathVariable("imageFileName") String imageFileName, HttpServletResponse response){
        folderOperationService.deleteFolderOperation(folderName,imageFileName);
        return new ResponseEntity<String>("folder:" + folderName + " deleted", HttpStatus.OK);
    }


}
