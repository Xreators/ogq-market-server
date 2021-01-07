package com.ogqcorp.market.repository.error.exception.content;

import static com.ogqcorp.market.common.error.ErrorCodes.DomainErrorCodes.NOT_FOUND_ARTWORK_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:56 오후 Last Modified At: 2020/12/17
 */
public class NotFoundArtworkException extends MarketException {

  public NotFoundArtworkException(long seqId) {
    super(NOT_FOUND_ARTWORK_EXCEPTION, "not found artwork: sequence(" + seqId + ")");
  }
  public NotFoundArtworkException(String artworkId) {
    super(NOT_FOUND_ARTWORK_EXCEPTION, "not found artwork: " + artworkId);
  }
}
