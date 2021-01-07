package com.ogqcorp.market.ontology.config;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/10/05 6:46 오후 Last Modified At: 2020/10/05
 */
public class OntologyInitialParam {
  private String attestationUrl;
  private String rpcChainNodeUrl;
  private String restfulChainNodeUrl;
  private String contractAddress;
  private String addonId;
  private String tenantId;
  private OntologyCommType ontologyCommType;

  private OntologyInitialParam(){}

  public String getAttestationUrl() {
    return attestationUrl;
  }

  public String getRpcChainNodeUrl() {
    return rpcChainNodeUrl;
  }

  public String getRestfulChainNodeUrl() { return restfulChainNodeUrl; }

  public String getContractAddress() {
    return contractAddress;
  }

  public String getAddonId() {
    return addonId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public OntologyCommType getOntologyCommType() {
    return ontologyCommType;
  }

  public enum OntologyCommType {
    RPC, RESTFUL
  }

  public static OntologyInitialParamBuilder builder() {
    return new OntologyInitialParamBuilder();
  }

  public static class OntologyInitialParamBuilder {
    private OntologyInitialParam ontologyInitialParam;

    private OntologyInitialParamBuilder(){
      this.ontologyInitialParam = new OntologyInitialParam();
    }

    public OntologyInitialParamBuilder attestationUrl(String attestationUrl) {
      ontologyInitialParam.attestationUrl = attestationUrl;
      return this;
    }

    public OntologyInitialParamBuilder rpcChainNodeUrl(String rpcChainNodeUrl) {
      ontologyInitialParam.rpcChainNodeUrl = rpcChainNodeUrl;
      return this;
    }

    public OntologyInitialParamBuilder restfulChainNodeUrl(String restfulChainNodeUrl) {
      ontologyInitialParam.restfulChainNodeUrl = restfulChainNodeUrl;
      return this;
    }
    public OntologyInitialParamBuilder ontologyCommType(OntologyCommType ontologyCommType) {
      ontologyInitialParam.ontologyCommType = ontologyCommType;
      return this;
    }

    public OntologyInitialParamBuilder contractAddress(String contractAddress) {
      ontologyInitialParam.contractAddress = contractAddress;
      return this;
    }

    public OntologyInitialParamBuilder addonId(String addonId) {
      ontologyInitialParam.addonId = addonId;
      return this;
    }

    public OntologyInitialParamBuilder tenantId(String tenantId) {
      ontologyInitialParam.tenantId = tenantId;
      return this;
    }

    public OntologyInitialParam build() {
      return ontologyInitialParam;
    }

  }

}
