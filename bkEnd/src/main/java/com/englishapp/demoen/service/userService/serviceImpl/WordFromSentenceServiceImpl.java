package com.englishapp.demoen.service.userService.serviceImpl;

import com.englishapp.demoen.entity.WordFromSentence;
import com.englishapp.demoen.repository.WordFromSentenceRepository;
import com.englishapp.demoen.service.userService.WordFromSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordFromSentenceServiceImpl implements WordFromSentenceService {

    @Autowired
    private WordFromSentenceRepository wordFromSentenceRepository;


    @Override
    public WordFromSentence findWordFromSentenceById(Long id) {
        return wordFromSentenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<WordFromSentence> findAllByTextAudioId(Iterable<Long> textAudioId) {
        return wordFromSentenceRepository.findAllById(textAudioId);
    }
}
