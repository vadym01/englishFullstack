package com.englishapp.demoen.controller.user;

import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.FileDataInfoUserLayerAccessDto;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class FileManageUserAccess {

    @Autowired
    private FileDataInfoService fileDataInfoService;

    @GetMapping("/getAllAudio/{bucketFolderName}")
    public ResponseEntity<?> getAllAudioFromFolder(@PathVariable String bucketFolderName){
        List<FileDataInfoUserLayerAccessDto> files = fileDataInfoService.getAllAudioFiledForUser(bucketFolderName);

        return new ResponseEntity<>(files, HttpStatus.OK);
    }



}
