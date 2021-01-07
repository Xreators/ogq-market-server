package com.ogqcorp.market.ontology.res.transaction;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 10:38 오전 Last Modified At: 2020/05/28
 */
public class BlockDTO {
  private List<Transaction> Transactions;
  private TransactionHeader Header;
  private long Size;
  private String Hash;

  public BlockDTO() {}

  public List<Transaction> getTransactions() {
    return Transactions;
  }

  public TransactionHeader getHeader() {
    return Header;
  }

  public long getSize() {
    return Size;
  }

  public String getHash() {
    return Hash;
  }
}
