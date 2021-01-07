package com.ogqcorp.market.repository.error.exception.content;

import static com.ogqcorp.market.common.error.ErrorCodes.DomainErrorCodes.NOT_FOUND_CERTIFICATION_REQUEST_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/22 6:23 오후 Last Modified At: 2020/12/22
 */
public class NotFoundCertificationRequestException extends MarketException {

  public NotFoundCertificationRequestException(String artworkId) {
    super(NOT_FOUND_CERTIFICATION_REQUEST_EXCEPTION, "not found certification create request(" + artworkId + ")");
  }
}
