package com.quiz.quiz.model;

import com.quiz.common.BaseAuditableEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz extends BaseAuditableEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userId;

  private String quizTitle;

}
