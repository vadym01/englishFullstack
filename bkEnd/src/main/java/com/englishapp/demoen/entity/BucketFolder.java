package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
public class BucketFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String folderName;

    private String description;
    private String imageForFolder;
    private String imageFileName;

    @OneToMany(mappedBy = "bucketFolder",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference(value = "FileDataInfo-BucketFolder")
    private Set<FileDataInfo> fileDataInfoList = new HashSet<>();

    @ManyToMany(mappedBy = "fvBucketFolder", fetch = FetchType.LAZY)
//    @JsonBackReference(value = "User-BucketFolder")
    private Set<User> user = new HashSet<>();

    @OneToMany(mappedBy = "bucketFolder",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference(value="WordFomSentence-BucketFolder")
    private Set<WordFromSentence> wordFromSentences = new HashSet<>();

    @PreRemove
    private void removeGroupsFromUsers() {
        for (User u : user) {
            u.getFvBucketFolder().remove(this);
        }
    }

    public BucketFolder(Long id, String folderName, String description, Set<FileDataInfo> fileDataInfoList, Set<User> user, Set<WordFromSentence> wordFromSentences) {
        this.id = id;
        this.folderName = folderName;
        this.description = description;
        this.fileDataInfoList = fileDataInfoList;
        this.user = user;
        this.wordFromSentences = wordFromSentences;
    }

    public BucketFolder(String folderName, String description, String imageForFolder,String imageFileName) {
        this.folderName = folderName;
        this.description = description;
        this.imageForFolder = imageForFolder;
        this.imageFileName = imageFileName;
    }

    public BucketFolder() {
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageForFolder() {
        return imageForFolder;
    }

    public void setImageForFolder(String imageForFolder) {
        this.imageForFolder = imageForFolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FileDataInfo> getFileDataInfoList() {
        return fileDataInfoList;
    }

    public void setFileDataInfoList(Set<FileDataInfo> fileDataInfoList) {
        this.fileDataInfoList = fileDataInfoList;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<WordFromSentence> getWordFromSentences() {
        return wordFromSentences;
    }

    public void setWordFromSentences(Set<WordFromSentence> wordFromSentences) {
        this.wordFromSentences = wordFromSentences;
    }

    @Override
    public String toString() {
        return "BucketFolder{" +
                "id=" + id +
                ", folderName='" + folderName + '\'' +
                ", description='" + description + '\'' +
                ", fileDataInfoList=" + fileDataInfoList +
                ", user=" + user +
                ", wordFromSentences=" + wordFromSentences +
                '}';
    }
}
