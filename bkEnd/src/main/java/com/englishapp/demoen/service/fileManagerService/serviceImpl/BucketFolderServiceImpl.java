package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.englishapp.demoen.entity.BucketFolder;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.entity.dto.BucketFolderSelectWithFavoriteDTO;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.repository.UserRepository;
import com.englishapp.demoen.service.fileManagerService.BucketFolderService;
import com.englishapp.demoen.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BucketFolderServiceImpl implements BucketFolderService {

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    @Override
    public void deleteFolder(String folderName) {
        BucketFolder bucketFolder = bucketFolderRepository.findByFolderName(folderName);
        bucketFolderRepository.delete(bucketFolder);
    }

    @Override
    public BucketFolder saveNewFolder(BucketFolder bucketFolder) {
        return bucketFolderRepository.save(bucketFolder);
    }

    @Override
    public List<BucketFolder> getAllFolders() {
        return bucketFolderRepository.findAll();
    }


    @Override
    public BucketFolder findBucketFolderById(Long id) {
        return bucketFolderRepository.findById(id).get();
    }

    @Override
    public List<BucketFolderSelectWithFavoriteDTO> getAllFoldersWithFavoriteSelected() {

        User user= userService.getUserObj();
            List<BucketFolder> bucketFolderList = getAllFolders();
            List<BucketFolderSelectWithFavoriteDTO> bucketFolderSelectWithFavoriteDTOS = new ArrayList<>();
            bucketFolderList.stream().map(folder -> {
                return bucketFolderSelectWithFavoriteDTOS.add(new BucketFolderSelectWithFavoriteDTO(folder.getId(), folder.getFolderName(), folder.getDescription(),folder.getImageForFolder(),folder.getImageFileName(), user.getId(), folder.getUser().contains(user)));
            }).collect(Collectors.toList());
            return bucketFolderSelectWithFavoriteDTOS;

    }





}
