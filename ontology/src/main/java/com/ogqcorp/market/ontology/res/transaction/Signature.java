package com.ogqcorp.market.ontology.res.transaction;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 10:45 오전 Last Modified At: 2020/05/28
 */
public class Signature {
  private List<String> PubKeys;
  private List<String> SigData;
  private int M;

  public Signature() {}

  public List<String> getPubKeys() {
    return PubKeys;
  }

  public List<String> getSigData() {
    return SigData;
  }

  public int getM() {
    return M;
  }
}
