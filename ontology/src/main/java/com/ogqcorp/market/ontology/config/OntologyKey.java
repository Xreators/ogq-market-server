package com.ogqcorp.market.ontology.config;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/10/06 4:01 오후 Last Modified At: 2020/10/06
 */
public class OntologyKey {

  private String address;
  private String salt;
  private String label;
  private String type;
  private Parameters parameters;
  private Scrypt scrypt;
  private String key;
  private String algorithm;

  public String getAddress() {
    return address;
  }

  public String getSalt() {
    return salt;
  }

  public String getLabel() {
    return label;
  }

  public String getType() {
    return type;
  }

  public String getCurve() {
    return parameters.curve;
  }

  public int getDkLen() {
    return scrypt.dkLen;
  }

  public int getScryptN() {
    return scrypt.n;
  }

  public int getScryptP() { return scrypt.p; }

  public int getScryptR() {
    return scrypt.r;
  }

  public String getKey() {
    return key;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public static class Parameters {
    private String curve;

    public String getCurve() {
      return curve;
    }

  }
  public static class Scrypt {
    private int dkLen;
    private int n;
    private int p;
    private int r;
  }

}
