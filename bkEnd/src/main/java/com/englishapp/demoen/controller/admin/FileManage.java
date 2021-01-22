package com.englishapp.demoen.controller.admin;

import com.amazonaws.services.s3.AmazonS3;
import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.UserPageInfo;
import com.englishapp.demoen.entity.dto.TextAudioCustomLoadThrough;
import com.englishapp.demoen.entity.dto.TextAudioCustomSentence;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.service.MapValidationErrorService;
import com.englishapp.demoen.service.fileManagerService.*;
import com.englishapp.demoen.service.userService.UserPageInfoService;
import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/admin/fileManage")
@CrossOrigin
public class FileManage {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AmazonS3ManagerService amazonS3ManagerService;

    @Autowired
    private FolderOperationService folderOperationService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private BucketFolderService bucketFolderService;

    //deleteMe
    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    //deleteMe
    @Autowired
    private FileDataInfoService fileDataInfoService;

    @Autowired
    private FileOperationService fileOperationService;

    @Autowired
    private TextAudioService textAudioService;

    @Autowired
    private UserPageInfoService userPageInfoService;


    //should return created file instead of all files (issue with react)
    @PostMapping("/addFile")
    public ResponseEntity<?> uploadFile( @RequestPart("file") MultipartFile file,
                             @RequestParam("fileName")String fileName,
                             @RequestParam("startNewAudioLoadTrough") int startNewAudioLoadTrough,
                             @RequestParam("sentence")String sentence,
                             @RequestParam("fileFolder")String fileFolder){


//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
//        if(errorMap != null){
//            return errorMap;
//        }

        FileDataInfo fileDataInfo = folderOperationService.uploadFile(file,fileName,startNewAudioLoadTrough,sentence,fileFolder);
        return new ResponseEntity<FileDataInfo>(fileDataInfo,HttpStatus.CREATED);
    }



    @DeleteMapping("/{folderName}/{fileName}")
    public void deleteFile(@PathVariable("folderName") String folderName,@PathVariable("fileName") String fileName){

        fileOperationService.deleteFile(folderName,fileName);
//        return new ResponseEntity<String>("deleted",HttpStatus.OK);
    }

    @PutMapping("/newTxtAudioSentence")
    @ResponseBody
    public ResponseEntity<FileDataInfo> changeTextAudioSentence(@RequestBody TextAudioCustomSentence textAudioCustomSentence){
         FileDataInfo fileDataInfo =  textAudioService.changeSentence(textAudioCustomSentence.getId(),textAudioCustomSentence.getSentence());
         return new ResponseEntity<FileDataInfo>(fileDataInfo,HttpStatus.OK);
    }

    @GetMapping("/{folderName}")
    public List<FileDataInfo> getAllFromFolder(@PathVariable("folderName")String folderName){
        return fileDataInfoService.getAllByBucketFolderName(folderName);
    }
    
    @PutMapping("/newAudioLoadThrough")
    @ResponseBody
    public ResponseEntity<?> changeStartNewAudioLoadThrough (@Valid @RequestBody TextAudioCustomLoadThrough customTextAudio){

//        ResponseEntity <?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
//
//
//        if(errorMap!= null){
//            return errorMap;
//        }

        FileDataInfo fileDataInfo = textAudioService.changeStartNewAudioLoadThrough(customTextAudio.getId(),customTextAudio.getTime());
        return new ResponseEntity<TextAudioCustomLoadThrough>(customTextAudio,HttpStatus.OK);
    }
    
    
}
