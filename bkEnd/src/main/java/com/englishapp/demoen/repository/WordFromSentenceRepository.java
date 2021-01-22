package com.englishapp.demoen.repository;

import com.englishapp.demoen.entity.WordFromSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordFromSentenceRepository extends JpaRepository<WordFromSentence, Long> {
}
