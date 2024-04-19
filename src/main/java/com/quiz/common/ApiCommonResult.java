package com.quiz.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the common return type for all of our api.It will wrap
 * the data by individual service into the common result.
 * And all error handling will be done in this class.
 * Everything returned by API must be human-readable and no exception
 * should be thrown.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiCommonResult<T> implements Serializable {
  /**
   * Data from individual api.
   */
  private T data;

  private String errorMessage;
  /**
   * Indicator for success or fail.
   */
  private boolean success;

  public static <T> ApiCommonResult<T> createSuccessCommonResult(T data) {
    return new ApiCommonResult<>(data, "", true);
  }

  public static ApiCommonResult<String> createDefaultSuccessCommonResult() {
    return new ApiCommonResult<>("", "", true);
  }

  public static <T> ApiCommonResult<T> createErrorCommonResult(ApiCommonResult<?> result) {
    return createErrorCommonResult(result.errorMessage);
  }

  public static <T> ApiCommonResult<T> createErrorCommonResult(String errorMessage) {
    return new ApiCommonResult<>(null, errorMessage, false);
  }
}
