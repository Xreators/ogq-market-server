package com.ogqcorp.market.ontology.res.contract;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 3:28 오후 Last Modified At: 2020/05/28
 */
public class Notify {
  private List<Object> States;
  private String ContractAddress;

  public Notify() {}

  public List<Object> getStates() {
    return States;
  }

  public String getContractAddress() {
    return ContractAddress;
  }

  @Override
  public String toString() {
    return "Notify{" +
        "States=" + States +
        ", ContractAddress='" + ContractAddress + '\'' +
        '}';
  }
}
