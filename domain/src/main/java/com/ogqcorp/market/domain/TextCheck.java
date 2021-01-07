package com.ogqcorp.market.domain;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:00 오전 Last Modified At: 2020/12/09
 */
public enum TextCheck {
  PASS(0),
  EMPTY_VALUE(1),
  TOO_SHORT(2),
  TOO_LONG(3),
  EXPR_VIOLATION(4);

  private final int value;
  private TextCheck(int value) {
    this.value = value;
  }
  public int toCode() {
    return value;
  }
}
