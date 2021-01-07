package com.ogqcorp.market.common.handler.response;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:44 오후 Last Modified At: 2020/12/07
 */
public class BaseResponse {
  int code;

  public BaseResponse(int code) {
    this.code = code;
  }

  public BaseResponse() {}

  public int getCode() {
    return code;
  }
}
