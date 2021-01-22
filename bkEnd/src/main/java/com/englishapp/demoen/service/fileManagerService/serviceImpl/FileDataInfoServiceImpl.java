package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.englishapp.demoen.entity.*;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.FileDataInfoUserLayerAccessDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.TextAudioUserAccessDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.WordsFromSentenceDto;
import com.englishapp.demoen.exceptions.FileNameAlreadyExistException;
import com.englishapp.demoen.exceptions.FileNameAlreadyExistResponse;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.repository.FileDataInfoRepository;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import com.englishapp.demoen.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDataInfoServiceImpl implements FileDataInfoService {

    @Autowired
    private FileDataInfoRepository fileDataInfoRepository;

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Autowired
    private BucketFolderService bucketFolderService;

    @Autowired
    private FileDataInfoService fileDataInfoService;

    @Autowired
    private UserService userService;

    @Override
    public FileDataInfo saveNewFileData(String fileName, String contentType, Long fileSize, String fileUrl,String fileFolder, String sentence, int startNewAudioLoadTrough){
        BucketFolder bucketFolder = bucketFolderRepository.findByFolderName(fileFolder);

        List<WordFromSentence> wordFromSentences = new ArrayList<>();

        TextAudio textAudio = new TextAudio(sentence,sentence.length(),startNewAudioLoadTrough,wordFromSentences);

        String [] arr = sentence.split(" ");
        Arrays.stream(arr).forEach(word -> {
            WordFromSentence wordFromSentence = new WordFromSentence(word,bucketFolder,textAudio);
            wordFromSentences.add(wordFromSentence);
        });

        FileDataInfo fileDataInfo = new FileDataInfo(fileName,contentType,fileSize,fileUrl,textAudio,bucketFolder);

        try{
            fileDataInfoRepository.save(fileDataInfo);

        }catch (Exception ex){
            throw  new FileNameAlreadyExistException("File with name '" + fileName + "' already exist");
        }

        return fileDataInfo;
    }

    @Override
    public FileDataInfo findOneById(Long id) {
        return fileDataInfoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteFile(BucketFolder bktFolder, String fileName) {
        BucketFolder bucketFolder = bktFolder;
        FileDataInfo fileDataInfo = fileDataInfoRepository.findByBucketFolderAndFileName(bucketFolder,fileName);
         fileDataInfoRepository.deleteById(Long.valueOf(fileDataInfo.getId()));
        System.out.println(fileDataInfo.toString());
    }

    @Override
    public List<FileDataInfo> getAllByBucketFolderName(String folderName) {
        BucketFolder bucketFolder = bucketFolderRepository.findByFolderName(folderName);
        return fileDataInfoRepository.findByBucketFolder(bucketFolder);
    }

    @Override
    public List<FileDataInfo> getAllFiles() {
        return fileDataInfoRepository.findAll();
    }

    @Override
    public FileDataInfo findByTextAudio(TextAudio textAudio) {
        return fileDataInfoRepository.findByTextAudio(textAudio);
    }




    @Override
    public List<FileDataInfoUserLayerAccessDto> getAllAudioFiledForUser(String bucketFolderName) {
        User user = userService.getUserObj();
        List<FileDataInfoUserLayerAccessDto> fileDataInfoUserLayerAccessDto = new ArrayList<>();

        List<FileDataInfo> bucketFolderList = fileDataInfoService.getAllByBucketFolderName(bucketFolderName);
        bucketFolderList.stream().map(file -> {

            TextAudio textAudio = file.getTextAudio();
            List<WordsFromSentenceDto> wordsFromSentenceDtoList = new ArrayList<>();
            textAudio.getWordFromSentences().stream().map(wordFromSentence -> wordsFromSentenceDtoList.add(new WordsFromSentenceDto(wordFromSentence.getId(), wordFromSentence.getWord()))).collect(Collectors.toList());

            TextAudioUserAccessDto textAudioDto = new TextAudioUserAccessDto(textAudio.getId(),textAudio.getSentence(), textAudio.getSentenceLength(), textAudio.getStartNewAudioLoadThrough(),wordsFromSentenceDtoList);
            fileDataInfoUserLayerAccessDto.add(new FileDataInfoUserLayerAccessDto(file.getId(), file.getFileUrl(),textAudioDto));

            return wordsFromSentenceDtoList;
        }
        ).collect(Collectors.toList());

        return fileDataInfoUserLayerAccessDto;

    }


}
