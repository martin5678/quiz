package com.quiz.quiz.service;

import com.quiz.quiz.model.Answer;
import com.quiz.quiz.model.AssembledQuestion;
import com.quiz.quiz.model.AssembledQuiz;
import com.quiz.quiz.model.CreateQuizRequest;
import com.quiz.quiz.model.CreateQuizResponse;
import com.quiz.quiz.model.Question;
import com.quiz.quiz.model.Quiz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssembleService {
  @Autowired
  private AnswerService answerService;
  @Autowired
  private QuestionService questionService;
  @Autowired
  private QuizService quizService;

  public AssembledQuiz getAssembledQuiz(String quizId) {
    Quiz quiz = quizService.find(quizId);

    List<Question> questions = questionService.findByQuizId(quiz.getId());

    List<AssembledQuestion> result = new ArrayList<>();

    for (Question question : questions) {
      List<Answer> answers = answerService.getByQuestionId(question.getId());
      AssembledQuestion aq = new AssembledQuestion();
      aq.setAnswer(answers);
      aq.setQuestion(question);
      result.add(aq);
    }

    AssembledQuiz abq = new AssembledQuiz();
    abq.setQuiz(quiz);
    abq.setQuestion(result);
    return abq;

  }

  @Transactional
  public CreateQuizResponse createAssembledQuiz(String quizTitle, String userId, CreateQuizRequest.Question[] questions) {
    System.out.println("here");
    System.out.println(quizTitle);
    System.out.println(userId);
    System.out.println(Arrays.toString(questions));
    // create quiz
    var quiz = quizService.create(new Quiz(userId, quizTitle));

    for (var question : questions) {
      // create question and link to quiz
      Question q = new Question();
      q.setQuestionType(question.getQuestionType());
      q.setQuizId(quiz.getId());
      q.setQuestionTitle(question.getQuestionTitle());

      var que = questionService.create(q);
      // create answer and link to question
      for (var answer : question.getAnswers()) {
        Answer a = new Answer();
        a.setQuestionId(que.getId());
        a.setAnswerCorrect(answer.isAnswerCorrect());
        a.setAnswerTitle(answer.getAnswerTitle());

        var ans = answerService.create(a);
      }
    }

    return new CreateQuizResponse(quiz.getId());
  }
}
