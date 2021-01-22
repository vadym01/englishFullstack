package com.englishapp.demoen.entity.dto;

public class TextAudioCustomSentence {

    private Long id;
    private String sentence;

    public TextAudioCustomSentence(Long id, String sentence) {
        this.id = id;
        this.sentence = sentence;
    }

    public TextAudioCustomSentence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
