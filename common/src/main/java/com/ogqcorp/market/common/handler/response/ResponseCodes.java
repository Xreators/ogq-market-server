package com.ogqcorp.market.common.handler.response;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:48 오후 Last Modified At: 2020/12/07
 */
public final class ResponseCodes {

  private ResponseCodes() {}

  public static final int OK                               = 200;
  public static final int UNAUTHORIZED                     = 401;
  public static final int BAD_REQUEST                      = 400;
  public static final int NOT_FOUND                        = 404;
  public static final int CONFLIT                          = 409;
  public static final int SERVER_ERROR                     = 500;

}
