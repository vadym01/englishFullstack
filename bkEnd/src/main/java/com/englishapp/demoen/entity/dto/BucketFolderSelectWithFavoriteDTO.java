package com.englishapp.demoen.entity.dto;

import com.englishapp.demoen.entity.User;

import java.util.Set;

public class BucketFolderSelectWithFavoriteDTO {

    private Long id;
    private String folderName;
    private String description;
    private Long userId;
    private boolean favoriteSelected;
    private String imageForFolder;
    private String imageFileName;

    public BucketFolderSelectWithFavoriteDTO(Long id, String folderName, String description,String imageForFolder,String imageFileName, Long userId, boolean favoriteSelected) {
        this.id = id;
        this.folderName = folderName;
        this.description = description;
        this.imageForFolder = imageForFolder;
        this.imageFileName = imageFileName;
        this.userId = userId;
        this.favoriteSelected = favoriteSelected;
    }


    public String getImageForFolder() {
        return imageForFolder;
    }

    public void setImageForFolder(String imageForFolder) {
        this.imageForFolder = imageForFolder;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isFavoriteSelected() {
        return favoriteSelected;
    }

    public void setFavoriteSelected(boolean favoriteSelected) {
        this.favoriteSelected = favoriteSelected;
    }
}
