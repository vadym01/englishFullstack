package com.englishapp.demoen.entity.dto.audioLoadInfoDto;

public class WordsFromSentenceDto {

    private Long wordId;
    private String word;

    public WordsFromSentenceDto(Long wordId, String word) {
        this.wordId = wordId;
        this.word = word;
    }

    public WordsFromSentenceDto() {
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
