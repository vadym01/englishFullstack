package com.englishapp.demoen.entity.dto.audioLoadInfoDto;

import com.englishapp.demoen.entity.dto.audioLoadInfoDto.WordsFromSentenceDto;

import java.util.List;

public class TextAudioUserAccessDto {

    private Long sentenceId;
    private String sentence;
    private int sentenceLength;
    private int startNewAudioLoadThrough;
    private List<WordsFromSentenceDto> wordFromSentenceList;

    public TextAudioUserAccessDto(Long sentenceId, String sentence, int sentenceLength, int startNewAudioLoadThrough, List<WordsFromSentenceDto> wordFromSentenceList) {
        this.sentenceId = sentenceId;
        this.sentence = sentence;
        this.sentenceLength = sentenceLength;
        this.startNewAudioLoadThrough = startNewAudioLoadThrough;
        this.wordFromSentenceList = wordFromSentenceList;
    }



    public TextAudioUserAccessDto() {
    }

    public Long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Long sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getSentenceLength() {
        return sentenceLength;
    }

    public void setSentenceLength(int sentenceLength) {
        this.sentenceLength = sentenceLength;
    }

    public int getStartNewAudioLoadThrough() {
        return startNewAudioLoadThrough;
    }

    public void setStartNewAudioLoadThrough(int startNewAudioLoadThrough) {
        this.startNewAudioLoadThrough = startNewAudioLoadThrough;
    }

    public List<WordsFromSentenceDto> getWordFromSentenceList() {
        return wordFromSentenceList;
    }

    public void setWordFromSentenceList(List<WordsFromSentenceDto> wordFromSentenceList) {
        this.wordFromSentenceList = wordFromSentenceList;
    }
}
