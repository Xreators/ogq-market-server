package com.ogqcorp.market.hash.service.repo.content;

import com.ogqcorp.market.domain.hash.ArtworkHashData;
import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 12:27 오후 Last Modified At: 2020/12/17
 */
public final class ArtworkHashDataService {
  public interface CreateArtworkHashData<I> extends Function<I, Mono<ArtworkHashData>> {}
  public interface ExistArtworkHashData<I> extends Function<I, Mono<Boolean>> {}

}
