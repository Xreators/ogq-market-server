package com.ogqcorp.market.ontology.config;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/10/14 6:03 오후 Last Modified At: 2020/10/14
 */
public class OntologyCfgParam {
  private String tenant_id;
  private String wallet_address;
  private String contract_address;
  private String url;
  private String addon_id;

  public String getTenant_id() {
    return tenant_id;
  }

  public String getWallet_address() {
    return wallet_address;
  }

  public String getContract_address() {
    return contract_address;
  }

  public String getUrl() {
    return url;
  }

  public String getAddon_id() {
    return addon_id;
  }
}
