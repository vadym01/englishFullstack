package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserPageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean favorite;
    private int numOfRepeats;
    private String wrongTypedWord;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_data_info_id")
//    @JsonManagedReference(value = "FileDataInfo-UserPageInfo")
    private FileDataInfo fileDataInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JsonManagedReference(value = "User-FileDataInfo")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_from_sentence_id")
//    @JsonManagedReference(value = "WordFromSentence-UserPageInfo")
    private WordFromSentence wordFromSentence;


    public UserPageInfo(Long id, boolean favorite, int numOfRepeats,String wrongTypedWord, FileDataInfo fileDataInfo, User user) {
        this.id = id;
        this.favorite = favorite;
        this.numOfRepeats = numOfRepeats;
        this.wrongTypedWord = wrongTypedWord;
        this.fileDataInfo = fileDataInfo;
        this.user = user;
    }



    public UserPageInfo() {
    }

    public WordFromSentence getWordFromSentence() {
        return wordFromSentence;
    }



    public void setWordFromSentence(WordFromSentence wordFromSentence) {
        this.wordFromSentence = wordFromSentence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getWrongTypedWord() {
        return wrongTypedWord;
    }

    public void setWrongTypedWord(String wrongTypedWord) {
        this.wrongTypedWord = wrongTypedWord;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getNumOfRepeats() {
        return numOfRepeats;
    }

    public void setNumOfRepeats(int numOfRepeats) {
        this.numOfRepeats = numOfRepeats;
    }

    public FileDataInfo getFileDataInfo() {
        return fileDataInfo;
    }

    public void setFileDataInfo(FileDataInfo fileDataInfo) {
        this.fileDataInfo = fileDataInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
