package com.quiz.quiz.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssembledQuiz {
  private Quiz quiz;

  private List<AssembledQuestion> question;

}
