package com.ogqcorp.market.domain.account.policy.market;

import static com.ogqcorp.market.domain.TextCheck.EMPTY_VALUE;
import static com.ogqcorp.market.domain.TextCheck.EXPR_VIOLATION;
import static com.ogqcorp.market.domain.TextCheck.PASS;
import static com.ogqcorp.market.domain.TextCheck.TOO_LONG;
import static com.ogqcorp.market.domain.TextCheck.TOO_SHORT;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.UserNamePolicy;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:29 오전 Last Modified At: 2020/12/09
 */
public final class MarketUserNamePolicy implements UserNamePolicy {
  private static final String USERNAME_EXPR = "(?=.*\\d{1,16})(?=.*[a-zA-Z]{1,16}).{4,16}$";
  private static final int MIN_USERNAME_LENGTH = 4;
  private static final int MAX_USERNAME_LENGTH = 16;

  @Override
  public TextCheck checkRule(String text) {
    String username = Optional.ofNullable(text).orElse("").trim();
    if(username.isEmpty()) {
      return EMPTY_VALUE;
    }
    else if(username.length() < MIN_USERNAME_LENGTH) {
      return TOO_SHORT;
    }
    else if(username.length() > MAX_USERNAME_LENGTH) {
      return TOO_LONG;
    }
    return Pattern.compile(USERNAME_EXPR).matcher(username).find() ? PASS : EXPR_VIOLATION;
  }
}
