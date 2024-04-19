package com.quiz.quiz.repo;

import com.quiz.quiz.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
  public List<Question> findByQuizId(String id);
}
