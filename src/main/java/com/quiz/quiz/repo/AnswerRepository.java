package com.quiz.quiz.repo;

import com.quiz.quiz.model.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
  public List<Answer> findByQuestionId(String id);
}
