package com.ogqcorp.market.ontology.res.transaction;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 10:41 오전 Last Modified At: 2020/05/28
 */
public class Transaction {
  private long Nonce;
  private String Payer;
  private int TxType;
  private int Version;
  private List<Signature> Sigs;
  private String Attributes;
  private long GasPrice;
  private Payload Payload;
  private long Height;
  private String Hash;
  private long GasLimit;

  public Transaction() {}

  public long getNonce() {
    return Nonce;
  }

  public String getPayer() {
    return Payer;
  }

  public int getTxType() {
    return TxType;
  }

  public int getVersion() {
    return Version;
  }

  public List<Signature> getSigs() {
    return Sigs;
  }

  public String getAttributes() {
    return Attributes;
  }

  public long getGasPrice() {
    return GasPrice;
  }

  public Payload getPayload() {
    return Payload;
  }

  public long getHeight() {
    return Height;
  }

  public String getHash() {
    return Hash;
  }

  public long getGasLimit() {
    return GasLimit;
  }
}
