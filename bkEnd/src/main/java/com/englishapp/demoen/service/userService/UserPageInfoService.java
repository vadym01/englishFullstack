package com.englishapp.demoen.service.userService;

import com.englishapp.demoen.controller.user.UserLearningActions;
import com.englishapp.demoen.entity.UserPageInfo;
import com.englishapp.demoen.entity.dto.UserPageInfoPostDto;
import com.englishapp.demoen.entity.dto.userPageInfoDtoRequest.UserPageInfoDto;

import java.util.List;


public interface UserPageInfoService {
    UserPageInfo setUserPageInfo(Long wordFromSentenceId,Long fileDataInfoId);
    void saveListToUserRepeatCollection(List<UserPageInfoPostDto> userPageInfoList);
    List<UserPageInfoDto> getRepeatCollection();
    UserPageInfo setFavUserPageInfoUnit(Long id);
    void deleteFromLearningList(Long id);
}
