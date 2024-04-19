package com.quiz.quiz.service;

import com.quiz.quiz.model.Answer;
import com.quiz.quiz.repo.AnswerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
  @Autowired
  private AnswerRepository answerRepository;

  public Answer create(Answer answer) {
    return answerRepository.save(answer);
  }

  public List<Answer> getByQuestionId(String id) {
    return answerRepository.findByQuestionId(id);
  }
}
