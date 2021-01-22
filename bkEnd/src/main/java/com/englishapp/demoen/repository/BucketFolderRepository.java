package com.englishapp.demoen.repository;

import com.englishapp.demoen.entity.BucketFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketFolderRepository extends JpaRepository<BucketFolder,Long> {
    BucketFolder findByFolderName(String folderName);


}
