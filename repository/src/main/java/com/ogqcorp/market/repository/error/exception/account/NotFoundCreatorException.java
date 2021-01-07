package com.ogqcorp.market.repository.error.exception.account;

import static com.ogqcorp.market.common.error.ErrorCodes.DomainErrorCodes.NOT_FOUND_CREATOR_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketExternalException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 5:26 오후 Last Modified At: 2020/12/09
 */
public class NotFoundCreatorException extends MarketExternalException {
  public NotFoundCreatorException(String creatorId) {
    super(NOT_FOUND_CREATOR_EXCEPTION, "not found creator: " + creatorId);
  }
}
