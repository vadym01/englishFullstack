package com.englishapp.demoen.repository;

import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.entity.UserPageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserPageInfoRepository extends JpaRepository<UserPageInfo,Long> {
    List<UserPageInfo> findByUser(User user);
    UserPageInfo findByWrongTypedWord(String wrongTypedWord);

}
