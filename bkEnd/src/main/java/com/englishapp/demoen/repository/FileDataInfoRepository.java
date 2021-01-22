package com.englishapp.demoen.repository;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.TextAudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDataInfoRepository extends JpaRepository<FileDataInfo,Long> {
    
    FileDataInfo findByBucketFolderAndFileName(BucketFolder bucketFolder,String fileName);
    FileDataInfo findByTextAudio(TextAudio textAudio);
    List<FileDataInfo> findByBucketFolder(BucketFolder bucketFolder);
}
