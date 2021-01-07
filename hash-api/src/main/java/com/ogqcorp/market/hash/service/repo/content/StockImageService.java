package com.ogqcorp.market.hash.service.repo.content;

import com.ogqcorp.market.domain.content.artwork.stockimage.StockImage;
import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:50 오후 Last Modified At: 2020/12/17
 */
public final class StockImageService {
  public interface FindStockImageBy<I> extends Function<I, Mono<StockImage>> {}
}
