package com.englishapp.demoen.service.fileManagerService.serviceImpl;

import com.englishapp.demoen.entity.FileDataInfo;
import com.englishapp.demoen.entity.TextAudio;
import com.englishapp.demoen.repository.TextAudioRepository;
import com.englishapp.demoen.service.fileManagerService.FileDataInfoService;
import com.englishapp.demoen.service.fileManagerService.TextAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextAudioServiceImpl implements TextAudioService {

  @Autowired
  private TextAudioRepository textAudioRepository;

  @Autowired
  private FileDataInfoService fileDataInfoService;

    @Override
    public FileDataInfo changeSentence(Long id, String sentence) {
        TextAudio textAudio = textAudioRepository.findById(id).get();
        textAudio.setSentence(sentence);
         textAudioRepository.save(textAudio);
        return fileDataInfoService.findByTextAudio(textAudio);
    }

    @Override
    public FileDataInfo changeStartNewAudioLoadThrough(Long id, int through) {

        TextAudio textAudio = textAudioRepository.findById(id).get();
        textAudio.setStartNewAudioLoadThrough(through);
        textAudioRepository.save(textAudio);
        return fileDataInfoService.findByTextAudio(textAudio);
    }

    @Override
    public TextAudio findTextAudioById(Long id) {
        return textAudioRepository.findById(id).get();
    }
}
