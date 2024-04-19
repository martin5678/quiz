package com.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizRequest {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Answer {
    String answerTitle;

    boolean answerCorrect;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Question {
    String questionTitle;

    String questionType;

    @JsonProperty("answers")
    Answer[] answers;
  }

  private String quizTitle;

  @JsonProperty("questions")
  private Question[] questions;

  private String userId;
}
