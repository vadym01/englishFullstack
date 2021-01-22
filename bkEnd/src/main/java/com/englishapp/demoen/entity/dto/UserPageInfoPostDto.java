package com.englishapp.demoen.entity.dto;

public class UserPageInfoPostDto {
    private boolean favorite;
    private Long fileDataInfoId;
    private int numOfRepeats;
    private String wrongTypedWord;
    private Long userId;
    private Long wordFromSentenceId;

    public UserPageInfoPostDto(boolean favorite, Long fileDataInfoId, int numOfRepeats,String wrongTypedWord, Long userId, Long wordFromSentenceId) {
        this.favorite = favorite;
        this.fileDataInfoId = fileDataInfoId;
        this.numOfRepeats = numOfRepeats;
        this.wrongTypedWord = wrongTypedWord;
        this.userId = userId;
        this.wordFromSentenceId = wordFromSentenceId;
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

    public Long getFileDataInfoId() {
        return fileDataInfoId;
    }

    public void setFileDataInfoId(Long fileDataInfoId) {
        this.fileDataInfoId = fileDataInfoId;
    }

    public int getNumOfRepeats() {
        return numOfRepeats;
    }

    public void setNumOfRepeats(int numOfRepeats) {
        this.numOfRepeats = numOfRepeats;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordFromSentenceId() {
        return wordFromSentenceId;
    }

    public void setWordFromSentenceId(Long wordFromSentenceId) {
        this.wordFromSentenceId = wordFromSentenceId;
    }


    @Override
    public String toString() {
        return "UserPageInfoPostDto{" +
                "favorite=" + favorite +
                ", fileDataInfoId=" + fileDataInfoId +
                ", numOfRepeats=" + numOfRepeats +
                ", wrongTypedWord='" + wrongTypedWord + '\'' +
                ", userId=" + userId +
                ", wordFromSentenceId=" + wordFromSentenceId +
                '}';
    }
}
