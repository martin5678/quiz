package com.quiz.quiz.service;

import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.repo.QuizRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
  @Autowired
  private QuizRepository quizRepository;

  public Quiz find(String id) {
    return quizRepository.findById(id).orElseThrow();
  }

  public Quiz create(Quiz quiz) {
    return quizRepository.save(quiz);
  }

  public List<Quiz> findByUserId(String id) {
    return quizRepository.findByUserId(id);
  }
}
