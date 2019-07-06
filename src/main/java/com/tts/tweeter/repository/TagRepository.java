package com.tts.tweeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tts.tweeter.model.Tag;
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
  Tag findByPhraseContainsIgnoreCase(String phrase);
}
