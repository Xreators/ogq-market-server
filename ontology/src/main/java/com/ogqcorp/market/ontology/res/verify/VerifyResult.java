package com.ogqcorp.market.ontology.res.verify;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/12 4:43 오후 Last Modified At: 2020/05/12
 */
public class VerifyResult {

  private String desc;
  private int error;
  private String id;
  private String jsonrpc;
  private Verify result;

  public VerifyResult() {}

  public String getDesc() {
    return desc;
  }

  public int getError() {
    return error;
  }

  public String getId() {
    return id;
  }

  public String getJsonrpc() {
    return jsonrpc;
  }

  public Verify getResult() {
    return result;
  }

  @Override
  public String toString() {
    return "VerifyResult{" +
        "desc='" + desc + '\'' +
        ", error=" + error +
        ", id='" + id + '\'' +
        ", jsonrpc='" + jsonrpc + '\'' +
        ", result=" + result +
        '}';
  }
}
