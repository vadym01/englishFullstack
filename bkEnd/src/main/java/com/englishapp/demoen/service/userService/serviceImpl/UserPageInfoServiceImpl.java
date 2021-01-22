package com.englishapp.demoen.service.userService.serviceImpl;

import com.englishapp.demoen.controller.user.UserLearningActions;
import com.englishapp.demoen.entity.*;
import com.englishapp.demoen.entity.dto.UserPageInfoPostDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.FileDataInfoUserLayerAccessDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.TextAudioUserAccessDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.WordsFromSentenceDto;
import com.englishapp.demoen.entity.dto.userPageInfoDtoRequest.UserPageInfoDto;
import com.englishapp.demoen.repository.FileDataInfoRepository;
import com.englishapp.demoen.repository.UserPageInfoRepository;
import com.englishapp.demoen.repository.UserRepository;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import com.englishapp.demoen.service.fileManagerService.TextAudioService;
import com.englishapp.demoen.service.userService.UserPageInfoService;
import com.englishapp.demoen.service.userService.UserService;
import com.englishapp.demoen.service.userService.WordFromSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPageInfoServiceImpl implements UserPageInfoService {

    @Autowired
    private UserPageInfoRepository userPageInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordFromSentenceService wordFromSentenceService;


    @Autowired
    private FileDataInfoRepository fileDataInfoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TextAudioService textAudioService;

    @Autowired
    private FileDataInfoService fileDataInfoService;

    @Override
    public UserPageInfo setUserPageInfo(Long wordFromSentenceId,Long fileDataInfoId) {

        User user = userService.getUserObj();
        WordFromSentence wordFromSentence = wordFromSentenceService.findWordFromSentenceById(wordFromSentenceId);

        FileDataInfo fileDataInfo = fileDataInfoRepository.findById(fileDataInfoId).orElse(null);

        UserPageInfo userPageInfo = new UserPageInfo();
        userPageInfo.setWordFromSentence(wordFromSentence);
        userPageInfo.setFileDataInfo(fileDataInfo);
        userPageInfo.setUser(user);
        return userPageInfoRepository.save(userPageInfo);
    }



    @Override
    public void saveListToUserRepeatCollection(List<UserPageInfoPostDto> userPageInfoList) {

        User user = userService.getUserObj();
        List<UserPageInfo> userPageInfos = new ArrayList<>();

        userPageInfoList.forEach(userPageInfoPostDto -> {
            UserPageInfo userPageInfoUpdate = userPageInfoRepository.findByWrongTypedWord(userPageInfoPostDto.getWrongTypedWord());

            if(userPageInfoUpdate == null){
            UserPageInfo userPageInfo = new UserPageInfo();
            userPageInfo.setFavorite(userPageInfoPostDto.isFavorite());
            userPageInfo.setNumOfRepeats(userPageInfoPostDto.getNumOfRepeats());
            userPageInfo.setWrongTypedWord(userPageInfoPostDto.getWrongTypedWord());
            userPageInfo.setFileDataInfo(fileDataInfoService.findOneById(userPageInfoPostDto.getFileDataInfoId()));
            userPageInfo.setUser(user);
            userPageInfo.setWordFromSentence(wordFromSentenceService.findWordFromSentenceById(userPageInfoPostDto.getWordFromSentenceId()));
            userPageInfos.add(userPageInfo);
            }else{
                userPageInfoUpdate.setFavorite(userPageInfoPostDto.isFavorite());
                userPageInfoUpdate.setNumOfRepeats(userPageInfoPostDto.getNumOfRepeats());
                userPageInfoUpdate.setWrongTypedWord(userPageInfoPostDto.getWrongTypedWord());
                userPageInfoRepository.save(userPageInfoUpdate);
            }
        });
        userPageInfoRepository.saveAll(userPageInfos);
    }

    @Override
    public List<UserPageInfoDto> getRepeatCollection() {
        User user = userService.getUserObj();
        List<UserPageInfo> userLearningActions =  userPageInfoRepository.findByUser(user);
        List<UserPageInfoDto> userPageInfoDtoList = new ArrayList<>();
        userLearningActions.forEach(userPageInfo -> {
            boolean favorite = userPageInfo.isFavorite();
            int numOfRepeats = userPageInfo.getNumOfRepeats();
            String fileUrl = userPageInfo.getFileDataInfo().getFileUrl();
            Long audioId = userPageInfo.getFileDataInfo().getId();
            Long wordFromSentenceId = userPageInfo.getWordFromSentence().getId();
            String wrongTypedWord = userPageInfo.getWrongTypedWord();

            TextAudio textAudio = textAudioService.findTextAudioById(userPageInfo.getFileDataInfo().getTextAudio().getId());
            wordFromSentenceService.findWordFromSentenceById(userPageInfo.getWordFromSentence().getId());

            List<WordsFromSentenceDto> wordsFromSentenceDtoList = new ArrayList<>();
            textAudio.getWordFromSentences().stream().map(searchWordFromSentence -> wordsFromSentenceDtoList.add(new WordsFromSentenceDto(searchWordFromSentence.getId(), searchWordFromSentence.getWord()))).collect(Collectors.toList());
            TextAudioUserAccessDto textAudioUserAccessDto = new TextAudioUserAccessDto(textAudio.getId(), textAudio.getSentence(), textAudio.getSentenceLength(),textAudio.getStartNewAudioLoadThrough(),wordsFromSentenceDtoList);
            FileDataInfoUserLayerAccessDto fileDataInfoUserLayerAccessDto = new FileDataInfoUserLayerAccessDto(audioId,fileUrl,textAudioUserAccessDto);
            UserPageInfoDto userPageInfoDto = new UserPageInfoDto(wordFromSentenceId,userPageInfo.getId(),audioId,favorite,numOfRepeats,wrongTypedWord,fileDataInfoUserLayerAccessDto,textAudioUserAccessDto);
             userPageInfoDtoList.add(userPageInfoDto);
        });
        return userPageInfoDtoList;
    }

    @Override
    public UserPageInfo setFavUserPageInfoUnit(Long id) {
        UserPageInfo userPageInfo = userPageInfoRepository.findById(id).get();
        userPageInfo.setFavorite(!userPageInfo.isFavorite());
        return userPageInfoRepository.save(userPageInfo);

    }


    @Override
    public void deleteFromLearningList(Long id) {
        userPageInfoRepository.deleteById(id);
    }
}

