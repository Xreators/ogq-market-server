package com.ogqcorp.market.hash.service.repo.content.impl;

import com.ogqcorp.market.domain.content.artwork.stockimage.StockImage;
import com.ogqcorp.market.hash.service.repo.content.StockImageService.FindStockImageBy;
import com.ogqcorp.market.repository.error.exception.content.NotFoundArtworkException;
import com.ogqcorp.market.repository.persistence.content.artwork.StockImageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:51 오후 Last Modified At: 2020/12/17
 */
@Service
public class FindStockImageBySeqId implements FindStockImageBy<Long> {

  private final StockImageRepository stockImageRepository;

  @Autowired
  public FindStockImageBySeqId(
      StockImageRepository stockImageRepository) {
    this.stockImageRepository = stockImageRepository;
  }

  @Override
  public Mono<StockImage> apply(Long seqId) {
    return Mono.fromCallable(() -> Optional.ofNullable(seqId))
      .publishOn(Schedulers.boundedElastic())
      .map(it -> it
        .map(stockImageRepository::findBySeqId)
        .orElseThrow(() -> new NotFoundArtworkException("null"))
      )
      .filter(Optional::isPresent)
      .switchIfEmpty(Mono.error(new NotFoundArtworkException(seqId)))
      .map(Optional::get)
      .onErrorResume(Mono::error);
  }

}
