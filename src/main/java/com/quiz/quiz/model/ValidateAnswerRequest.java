package com.quiz.quiz.model;

import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateAnswerRequest {
  private String quizId;

  /**
   * question_id -> answer_id -> true/false
   */
  private Map<String, Map<String, Boolean>> result;

}
