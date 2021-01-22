package com.englishapp.demoen.entity.dto.userPageInfoDtoRequest;

import com.englishapp.demoen.entity.WordFromSentence;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.FileDataInfoUserLayerAccessDto;
import com.englishapp.demoen.entity.dto.audioLoadInfoDto.TextAudioUserAccessDto;

public class UserPageInfoDto {
    private Long wordFromSentenceId;
    private Long userPageInfoId;
    private Long audioId;
    private boolean favorite;
    private int numOfRepeats;
    private String wrongTypedWord;
    private FileDataInfoUserLayerAccessDto fileDataInfoUserLayerAccessDto;
    private TextAudioUserAccessDto textAudioUserAccessDto;

    public UserPageInfoDto(Long wordFromSentenceId, Long userPageInfoId,Long audioId, boolean favorite, int numOfRepeats, String wrongTypedWord, FileDataInfoUserLayerAccessDto fileDataInfoUserLayerAccessDto, TextAudioUserAccessDto textAudioUserAccessDto) {
        this.wordFromSentenceId = wordFromSentenceId;
        this.userPageInfoId = userPageInfoId;
        this.audioId = audioId;
        this.favorite = favorite;
        this.numOfRepeats = numOfRepeats;
        this.wrongTypedWord = wrongTypedWord;
        this.fileDataInfoUserLayerAccessDto = fileDataInfoUserLayerAccessDto;
        this.textAudioUserAccessDto = textAudioUserAccessDto;
    }

    public UserPageInfoDto() {
    }

    public Long getWordFromSentenceId() {
        return wordFromSentenceId;
    }

    public void setWordFromSentenceId(Long wordFromSentenceId) {
        this.wordFromSentenceId = wordFromSentenceId;
    }

    public Long getUserPageInfoId() {
        return userPageInfoId;
    }

    public void setUserPageInfoId(Long userPageInfoId) {
        this.userPageInfoId = userPageInfoId;
    }

    public boolean isFavorite() {
        return favorite;
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

    public String getWrongTypedWord() {
        return wrongTypedWord;
    }

    public void setWrongTypedWord(String wrongTypedWord) {
        this.wrongTypedWord = wrongTypedWord;
    }

    public FileDataInfoUserLayerAccessDto getFileDataInfoUserLayerAccessDto() {
        return fileDataInfoUserLayerAccessDto;
    }

    public void setFileDataInfoUserLayerAccessDto(FileDataInfoUserLayerAccessDto fileDataInfoUserLayerAccessDto) {
        this.fileDataInfoUserLayerAccessDto = fileDataInfoUserLayerAccessDto;
    }

    public TextAudioUserAccessDto getTextAudioUserAccessDto() {
        return textAudioUserAccessDto;
    }

    public void setTextAudioUserAccessDto(TextAudioUserAccessDto textAudioUserAccessDto) {
        this.textAudioUserAccessDto = textAudioUserAccessDto;
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }
}
