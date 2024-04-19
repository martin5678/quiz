package com.quiz.quiz;

import static com.quiz.common.ApiCommonResult.createSuccessCommonResult;

import com.quiz.common.ApiCommonResult;
import com.quiz.quiz.model.AssembledQuiz;
import com.quiz.quiz.model.CreateQuizRequest;
import com.quiz.quiz.model.CreateQuizResponse;
import com.quiz.quiz.model.GetQuizRequest;
import com.quiz.quiz.model.ListQuizRequest;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.model.ValidateAnswerRequest;
import com.quiz.quiz.model.ValidateAnswerResponse;
import com.quiz.quiz.service.AssembleService;
import com.quiz.quiz.service.QuizService;
import java.util.List;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Quiz")
public class QuizController {

  @Autowired
  private AssembleService assembleService;

  @Autowired
  private QuizService quizService;

  /**
   * Get all quiz for a user
   *
   */
  @PostMapping("/List")
  public ResponseEntity<ApiCommonResult<List<Quiz>>> getQuizList(@RequestBody ListQuizRequest req) {
    return ResponseEntity.ok(createSuccessCommonResult(quizService.findByUserId(req.getUserId())));
  }

  /**
   * Get an already assembled quiz
   */
  @PostMapping("/Detail")
  public ResponseEntity<ApiCommonResult<AssembledQuiz>> getQuiz(@RequestBody GetQuizRequest req) {
    return ResponseEntity.ok(createSuccessCommonResult(assembleService.getAssembledQuiz(req.getQuizId())));
  }

  /**
   * Create a quiz from structured data
   */
  @PostMapping(path="/Create", consumes="application/json")
  public ResponseEntity<ApiCommonResult<CreateQuizResponse>> createQuiz(@RequestBody CreateQuizRequest req) {
    return ResponseEntity.ok(createSuccessCommonResult(assembleService.createAssembledQuiz(req.getQuizTitle(), req.getUserId(),req.getQuestions())));
  }

}
