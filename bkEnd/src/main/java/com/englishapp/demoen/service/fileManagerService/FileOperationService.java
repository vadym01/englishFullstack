package com.englishapp.demoen.service.fileManagerService;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileOperationService {


    File multipartToFileConverter(MultipartFile multipartFile) throws IOException;
    String fileNameGenerator(String fileNames);
    void deleteFile(String folderName,String fileName);



}
