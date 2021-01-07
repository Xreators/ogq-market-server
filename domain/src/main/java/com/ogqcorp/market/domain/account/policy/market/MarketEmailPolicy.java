package com.ogqcorp.market.domain.account.policy.market;

import static com.ogqcorp.market.domain.TextCheck.EMPTY_VALUE;
import static com.ogqcorp.market.domain.TextCheck.EXPR_VIOLATION;
import static com.ogqcorp.market.domain.TextCheck.PASS;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.EmailPolicy;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:10 오전 Last Modified At: 2020/12/09
 */
public final class MarketEmailPolicy implements EmailPolicy {

  private static final String EMAIL_EXPR = "^([a-zA-Z0-9_.-]+@[\\da-zA-Z.-]+\\.[a-zA-Z.]{2,6})$";

  @Override
  public TextCheck checkRule(String text) {
    return Optional.ofNullable(text)
      .map(email ->
        Pattern.compile(EMAIL_EXPR).matcher(email.trim()).find() ?
          PASS :
          EXPR_VIOLATION
      )
      .orElseGet(() -> EMPTY_VALUE);
  }

}
