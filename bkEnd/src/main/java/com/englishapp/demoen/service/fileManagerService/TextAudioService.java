package com.englishapp.demoen.service.fileManagerService;

import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.TextAudio;

public interface TextAudioService {

    FileDataInfo changeSentence(Long id, String sentence);
    FileDataInfo changeStartNewAudioLoadThrough(Long id, int through);
    TextAudio findTextAudioById(Long id);
}
