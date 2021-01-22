package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.*;

@Entity
//@Data
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
//@JsonIdentityReference(alwaysAsId=true)
public class FileDataInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private  String fileName;

    private  String contentType;

    private  long fileSize;

    private String fileUrl;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bucket_folder_id")
//    @JsonManagedReference(value = "BucketFolder-FileDataInfo")
    private BucketFolder bucketFolder;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created;


//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_audio_id")
//    @JsonManagedReference(value = "TextAudio-FileDataInfo")
//    @JsonBackReference(value = "TextAudio-FileDataInfo")
    private  TextAudio textAudio;

    @OneToMany(mappedBy = "fileDataInfo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference(value = "UserPageInfo-FileDataInfo")
    private Set<UserPageInfo> userPageInfo = new HashSet<>();


    @PrePersist
    protected void updateDate(){
        created = new Date();
    }

    public FileDataInfo() {
    }

    public FileDataInfo(String fileName, String contentType, long fileSize, String fileUrl, TextAudio textAudio,BucketFolder bucketFolder) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileUrl = fileUrl;
        this.textAudio = textAudio;
        this.bucketFolder = bucketFolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public BucketFolder getBucketFolder() {
        return bucketFolder;
    }

    public void setBucketFolder(BucketFolder bucketFolder) {
        this.bucketFolder = bucketFolder;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public TextAudio getTextAudio() {
        return textAudio;
    }

    public void setTextAudio(TextAudio textAudio) {
        this.textAudio = textAudio;
    }

    public Set<UserPageInfo> getUserPageInfo() {
        return userPageInfo;
    }

    public void setUserPageInfo(Set<UserPageInfo> userPageInfo) {
        this.userPageInfo = userPageInfo;
    }
}
