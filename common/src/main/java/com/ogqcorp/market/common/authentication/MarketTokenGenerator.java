package com.ogqcorp.market.common.authentication;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:37 오후 Last Modified At: 2020/12/07
 */

public class MarketTokenGenerator {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private String secret;

  private final String NAVER_OGQ_MARKET_NAME = "NOM";

  private final String TRANS_ID = "tId";
  private final String MARKET_ID = "mId";
  private final String USER_ID = "uId";
  private final String USER_NAME = "name";
  private final String NICK_NAME = "nick";
  private final String USER_TYPE = "type";
  private final String USER_IMAGE = "image";
  private final String USER_STATUS = "status";
  private final String USER_ROLE = "role";
  private final String MARKET_USER_ID = "muId";
  private final String EXTRA = "extra";

  public MarketTokenGenerator(String secret) {
    this.secret = secret;
  }

  public String generateToken(MarketToken mToken) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create().withIssuer(NAVER_OGQ_MARKET_NAME)
        .withIssuedAt(new Date(mToken.getCreatedAt().toEpochSecond() * 1000))
        .withClaim(TRANS_ID, mToken.getTransId())
        .withClaim(MARKET_ID, mToken.getMarketId())
        .withClaim(USER_ID, mToken.getUserId())
        .withClaim(USER_NAME, mToken.getUsername())
        .withClaim(NICK_NAME, mToken.getNickName())
        .withClaim(USER_TYPE, mToken.getUserType())
        .withClaim(USER_IMAGE, mToken.getProfileUrl())
        .withClaim(USER_STATUS, mToken.getStatus())
        .withClaim(USER_ROLE, mToken.getRole())
        .withClaim(MARKET_USER_ID, mToken.getMarketUserId())
        .withClaim(EXTRA, mToken.getExtra())
        .sign(algorithm);
  }

  public MarketToken fromToken(String token) {
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
    DecodedJWT jwt = jwtVerifier.verify(token);

    return new MarketToken(jwt.getClaim(TRANS_ID).asString(),
        jwt.getClaim(MARKET_ID).asString(),
        jwt.getClaim(USER_ID).asString(),
        jwt.getClaim(USER_NAME).asString(),
        jwt.getClaim(NICK_NAME).asString(),
        jwt.getClaim(USER_TYPE).asString(),
        jwt.getClaim(USER_IMAGE).asString(),
        jwt.getClaim(USER_STATUS).asString(),
        jwt.getClaim(USER_ROLE).asString(),
        jwt.getClaim(MARKET_USER_ID).asString(),
        jwt.getClaim(EXTRA).asString(),
        ZonedDateTime
            .ofInstant(Instant.ofEpochMilli(jwt.getIssuedAt().getTime()), ZoneOffset.UTC));
  }
}

