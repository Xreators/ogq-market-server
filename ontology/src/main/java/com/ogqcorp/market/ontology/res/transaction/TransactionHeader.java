package com.ogqcorp.market.ontology.res.transaction;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 10:38 오전 Last Modified At: 2020/05/28
 */
public class TransactionHeader {
  private String TransactionsRoot;
  private String ConsensusPayload;
  private int Version;
  private List<String> SigData;
  private String PrevBlockHash;
  private String NextBookkeeper;
  private List<String> Bookkeepers;
  private long Height;
  private long ConsensusData;
  private String Hash;
  private long Timestamp;
  private String BlockRoot;

  public TransactionHeader() {}

  public String getTransactionsRoot() {
    return TransactionsRoot;
  }

  public String getConsensusPayload() {
    return ConsensusPayload;
  }

  public int getVersion() {
    return Version;
  }

  public List<String> getSigData() {
    return SigData;
  }

  public String getPrevBlockHash() {
    return PrevBlockHash;
  }

  public String getNextBookkeeper() {
    return NextBookkeeper;
  }

  public List<String> getBookkeepers() {
    return Bookkeepers;
  }

  public long getHeight() {
    return Height;
  }

  public long getConsensusData() {
    return ConsensusData;
  }

  public String getHash() {
    return Hash;
  }

  public long getTimestamp() {
    return Timestamp;
  }

  public String getBlockRoot() {
    return BlockRoot;
  }
}
