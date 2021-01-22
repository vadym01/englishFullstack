package com.englishapp.demoen.service.userService;

import com.englishapp.demoen.entity.WordFromSentence;

import java.util.List;

public interface WordFromSentenceService {
    WordFromSentence findWordFromSentenceById(Long id);
    List<WordFromSentence> findAllByTextAudioId(Iterable<Long> textAudioId);
}
