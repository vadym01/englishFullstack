package com.englishapp.demoen.service.fileManagerService;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.TextAudio;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.FileDataInfoUserLayerAccessDto;

import java.util.List;

public interface FileDataInfoService {

    FileDataInfo saveNewFileData(String fileName, String contentType, Long fileSize, String fileUrl,String fileFolder, String sentence, int startNewAudioLoadTrough);
    FileDataInfo findOneById(Long id);
    void deleteFile(BucketFolder bucketFolder, String fileFolder);
    List<FileDataInfo> getAllByBucketFolderName(String folderName);
    List<FileDataInfo> getAllFiles();
    FileDataInfo findByTextAudio(TextAudio textAudio);
//    List<FileDataInfo> findAllByBucketFolder(BucketFolder bucketFolder);
    List<FileDataInfoUserLayerAccessDto> getAllAudioFiledForUser(String bucketFolder);

}
