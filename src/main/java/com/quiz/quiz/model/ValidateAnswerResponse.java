package com.quiz.quiz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateAnswerResponse {
  private String correct;
  private String total;
}
