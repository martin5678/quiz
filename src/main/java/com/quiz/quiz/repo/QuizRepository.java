package com.quiz.quiz.repo;

import com.quiz.quiz.model.Quiz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
  public List<Quiz> findByUserId(String id);
}
