package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TextAudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull(message = "Sentence is mandatory")
    private  String sentence;
    @NotNull(message = "Sentence length is mandatory")
    private  int sentenceLength;
    @NotNull(message = "New audio load through: is mandatory")
    private  int startNewAudioLoadThrough;

    @OneToOne(mappedBy = "textAudio",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(name = "file_data_info_id")
    @JsonBackReference(value = "TextAudio-FileDataInfo")
    private FileDataInfo fileDataInfo;

    @OneToMany(mappedBy = "textAudio",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference("WordFomSentence-TextAudio")
    private List<WordFromSentence > wordFromSentences = new ArrayList<>();

    public TextAudio() {
    }




    public TextAudio(String sentence, int sentenceLength, int startNewAudioLoadThrough, List<WordFromSentence> wordFromSentences) {
        this.sentence = sentence;
        this.sentenceLength = sentenceLength;
        this.startNewAudioLoadThrough = startNewAudioLoadThrough;
        this.wordFromSentences = wordFromSentences;
    }

    public FileDataInfo getFileDataInfo() {
        return fileDataInfo;
    }

    public void setFileDataInfo(FileDataInfo fileDataInfo) {
        this.fileDataInfo = fileDataInfo;
    }

    public List<WordFromSentence> getWordFromSentences() {
        return wordFromSentences;
    }

    public void setWordFromSentences(List<WordFromSentence> wordFromSentences) {
        this.wordFromSentences = wordFromSentences;
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

//    public FileDataInfo getFileData() {
//        return fileDataInfo;
//    }
//
//    public void setFileData(FileDataInfo fileDataInfo) {
//        this.fileDataInfo = fileDataInfo;
//    }
}
