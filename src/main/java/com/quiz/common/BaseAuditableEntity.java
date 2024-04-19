package com.quiz.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class BaseAuditableEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "shortUUID-6")
  @GenericGenerator(
      name = "shortUUID-6",
      strategy = "com.quiz.util.ShortIdGenerator",
      parameters = {
          @Parameter(name = "length", value = "6"),
      }
  )
  private String id;

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Instant updatedAt;

  @JsonIgnore
  private Instant deletedAt;

  @PreRemove
  public void delete() {
    this.deletedAt = Instant.now();
  }
}
