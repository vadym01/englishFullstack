package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.UserPageInfo;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.repository.FileDataInfoRepository;
import com.englishapp.demoen.repository.TextAudioRepository;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import com.englishapp.demoen.service.fileManagerService.FileOperationService;
import com.englishapp.demoen.service.userService.UserPageInfoService;
import com.englishapp.demoen.service.userService.serviceImpl.UserPageInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TextAudioServiceImplTest {



    @Autowired
    private BucketFolderService bucketFolderService;

    @Autowired
    private FileDataInfoRepository fileDataInfoRepository;

    @Autowired
    private FileOperationService fileOperationService;

    @Autowired
    private UserPageInfoService userPageInfoService;
//
//    @Autowired
//    private UserPageInfo userPageInfo;

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Autowired
    private FileDataInfoService fileDataInfoService;


    @Test
    void changeStartNewAudioLoadThrough() {
    }


    @Test
    void findTst(){

    }

    @Test
    @Transactional
    void deleteFile(){
//        BucketFolder bucketFolder = bucketFolderService.findBucketFolderById(5L);
//        System.out.println(bucketFolder);
//        FileDataInfo fileDataInfo = fileDataInfoRepository.findByBucketFolderAndFileName(bucketFolder,"fater_delete_file");
//        System.out.println(fileDataInfo);
////        fileDataInfoService.deleteFile(bucketFolder, fileDataInfo.getFileName());
//        fileDataInfoRepository.deleteById(1l);
////        bucketFolderRepository.deleteById(1l);
////        System.out.println(fileDataInfo);
////        fileDataInfoService.deleteFile(bucketFolder,fileDataInfo.getFileName());
//
//
////        System.out.println(bucketFolderRepository.findById(5L));
////        System.out.println(bucketFolderService.findBucketFolderById(5L));

//        fileOperationService.deleteFile("The Lady, or the Tiger .part 1",);
    }
}