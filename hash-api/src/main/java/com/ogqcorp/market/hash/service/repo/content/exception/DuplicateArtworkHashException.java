package com.ogqcorp.market.hash.service.repo.content.exception;

import static com.ogqcorp.market.common.error.ErrorCodes.DomainErrorCodes.DUPLICATE_ARTWORK_HASH_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 5:19 오후 Last Modified At: 2020/12/17
 */
public class DuplicateArtworkHashException extends MarketException {

  public DuplicateArtworkHashException(String message) {
    super(DUPLICATE_ARTWORK_HASH_EXCEPTION, message);
  }
}
