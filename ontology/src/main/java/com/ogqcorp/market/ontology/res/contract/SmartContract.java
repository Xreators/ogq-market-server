package com.ogqcorp.market.ontology.res.contract;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 3:27 오후 Last Modified At: 2020/05/28
 */
public class SmartContract {
  private long GasConsumed;
  private List<Notify> Notify;
  private String TxHash;
  private int State;

  public SmartContract() {}

  public long getGasConsumed() {
    return GasConsumed;
  }

  public List<Notify> getNotify() {
    return Notify;
  }

  public String getTxHash() {
    return TxHash;
  }

  public int getState() {
    return State;
  }

  @Override
  public String toString() {
    return "SmartContract{" +
        "GasConsumed=" + GasConsumed +
        ", Notify=" + Notify +
        ", TxHash='" + TxHash + '\'' +
        ", State=" + State +
        '}';
  }
}
