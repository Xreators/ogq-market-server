package com.ogqcorp.market.ontology.res.batch;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/08 2:32 오후 Last Modified At: 2020/05/08
 */
public class BatchAddResult {

  private String desc;
  private int error;
  private String id;
  private String jsonrpc;
  private List<String> result;

  public BatchAddResult() {}

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

  public List<String> getResult() {
    return result;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setError(int error) {
    this.error = error;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  public void setResult(List<String> result) {
    this.result = result;
  }
}
