package com.ogqcorp.market.relay.service.repo.dto;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:25 오전 Last Modified At: 2020/12/21
 */
public class ErrorLogInsDTO {
  private long targetId;
  private int errorCode;
  private String message;

  public ErrorLogInsDTO(long targetId, int errorCode, String message) {
    this.targetId = targetId;
    this.errorCode = errorCode;
    this.message = message;
  }

  public long getTargetId() {
    return targetId;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }
}
