package com.quiz.quiz.service;

import com.quiz.quiz.model.Question;
import com.quiz.quiz.repo.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;

  public Question create(Question q) {
    return questionRepository.save(q);
  }

  public List<Question> findByQuizId(String id) {
    return questionRepository.findByQuizId(id);
  }
}
