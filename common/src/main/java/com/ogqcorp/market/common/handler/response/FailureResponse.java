package com.ogqcorp.market.common.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:46 오후 Last Modified At: 2020/12/07
 */
public class FailureResponse<T> extends BaseResponse{
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public FailureResponse(int code, String message) {
    super(code);
    this.message = message;
  }

  public FailureResponse(int code, String message, T data) {
    super(code);
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FailureResponse)) {
      return false;
    }
    FailureResponse<?> that = (FailureResponse<?>) o;
    return message.equals(that.message) && data.equals(that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, data);
  }
}
