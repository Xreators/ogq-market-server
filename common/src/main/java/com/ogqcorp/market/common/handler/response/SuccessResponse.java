package com.ogqcorp.market.common.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:47 오후 Last Modified At: 2020/12/07
 */
public class SuccessResponse<T> extends BaseResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public SuccessResponse() {
    super(ResponseCodes.OK);
    data = null;
  }

  public SuccessResponse(T data) {
    super(ResponseCodes.OK);
    this.data = data;
  }

  public T getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuccessResponse)) {
      return false;
    }
    SuccessResponse<?> that = (SuccessResponse<?>) o;
    return Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

}
