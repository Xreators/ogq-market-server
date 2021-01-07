package com.ogqcorp.market.repository.error.exception.content;

import static com.ogqcorp.market.common.error.ErrorCodes.DomainErrorCodes.NOT_FOUND_ARTWORK_HASH_DATA_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/28 12:14 오후 Last Modified At: 2020/12/28
 */
public class NotFoundArtworkHashData extends MarketException {

  public NotFoundArtworkHashData(String hash) {
    super(NOT_FOUND_ARTWORK_HASH_DATA_EXCEPTION, "not found artwork hash(" + hash + ")");
  }
}
