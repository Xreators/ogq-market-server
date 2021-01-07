package com.ogqcorp.market.common.authentication;

import java.security.Principal;
import java.time.ZonedDateTime;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:36 오후 Last Modified At: 2020/12/07
 */

public class MarketToken {
  private String transId;
  private String marketId;
  private String userId;
  private String username;
  private String nickName;
  private String userType;
  private String profileUrl;
  private String status;
  private String role;
  private String marketUserId;
  private String extra;
  private ZonedDateTime createdAt;

  public MarketToken(String transId, String marketId, String userId, String username, String nickName, String userType,
      String profileUrl, String status, String role, String marketUserId, String extra, ZonedDateTime createdAt) {
    this.transId = transId;
    this.marketId = marketId;
    this.userId = userId;
    this.username = username;
    this.nickName = nickName;
    this.userType = userType;
    this.profileUrl = profileUrl;
    this.status = status;
    this.role = role;
    this.marketUserId = marketUserId;
    this.extra = extra;
    this.createdAt = createdAt;
  }

  public MarketToken(String userId){
    this.userId = userId;
  }
  public MarketToken(String userId, String marketUserId){
    this.userId = userId;
    this.marketUserId = marketUserId;
  }

  public String getTransId() {
    return transId;
  }

  public void setTransId(String transId) {
    this.transId = transId;
  }

  public String getMarketId() {
    return marketId;
  }

  public void setMarketId(String marketId) {
    this.marketId = marketId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getProfileUrl() {
    return profileUrl;
  }

  public void setProfileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getMarketUserId() {
    return marketUserId;
  }

  public void setMarketUserId(String marketUserId) {
    this.marketUserId = marketUserId;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isAdmin() {
    return userType.equals("ADMIN");
  }

  public static MarketToken parsePrincipal(Principal principal) {
    return new MarketToken(principal.getName());
  }
}
