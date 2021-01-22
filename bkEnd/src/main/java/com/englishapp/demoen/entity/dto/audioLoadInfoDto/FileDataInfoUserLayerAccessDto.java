package com.englishapp.demoen.entity.dto.audioLoadInfoDto;

import com.englishapp.demoen.entity.dto.audioLoadInfoDto.TextAudioUserAccessDto;

public class FileDataInfoUserLayerAccessDto {

    private Long audioId;
    private String fileUrl;
    private TextAudioUserAccessDto textAudioUserAccessDto;

    public FileDataInfoUserLayerAccessDto(Long audioId,String fileUrl, TextAudioUserAccessDto textAudioUserAccessDto) {
        this.audioId = audioId;
        this.fileUrl = fileUrl;
        this.textAudioUserAccessDto = textAudioUserAccessDto;
    }


    public FileDataInfoUserLayerAccessDto() {
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public TextAudioUserAccessDto getTextAudioUserAccessDto() {
        return textAudioUserAccessDto;
    }

    public void setTextAudioUserAccessDto(TextAudioUserAccessDto textAudioUserAccessDto) {
        this.textAudioUserAccessDto = textAudioUserAccessDto;
    }
}
