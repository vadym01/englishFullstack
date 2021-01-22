package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class WordFromSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="bucket_folder_id")
//    @JsonManagedReference(value="BucketFolder-WordFromSentence")
    private BucketFolder bucketFolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_audio_id")
//    @JsonManagedReference(value="TextAudio-WordFromSentence")
    private TextAudio textAudio;

    @OneToMany(mappedBy = "wordFromSentence",cascade = CascadeType.ALL)
    @JsonBackReference(value = "UserPageInfo-WordFromSentence")
    private Set<UserPageInfo> userPageInfoSet = new HashSet<>();

    public WordFromSentence( String word, BucketFolder bucketFolder, TextAudio textAudio) {
        this.word = word;
        this.bucketFolder = bucketFolder;
        this.textAudio = textAudio;
    }


    public WordFromSentence() {
    }


    public Set<UserPageInfo> getUserPageInfoSet() {
        return userPageInfoSet;
    }

    public void setUserPageInfoSet(Set<UserPageInfo> userPageInfoSet) {
        this.userPageInfoSet = userPageInfoSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public BucketFolder getBucketFolder() {
        return bucketFolder;
    }

    public void setBucketFolder(BucketFolder bucketFolder) {
        this.bucketFolder = bucketFolder;
    }

    public TextAudio getTextAudio() {
        return textAudio;
    }

    public void setTextAudio(TextAudio textAudio) {
        this.textAudio = textAudio;
    }
}
