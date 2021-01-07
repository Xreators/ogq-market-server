package com.ogqcorp.market.relay.service.repo;

import com.ogqcorp.market.domain.content.artwork.ArtworkHash;
import java.util.function.Function;
import reactor.core.publisher.Flux;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:41 오전 Last Modified At: 2020/12/21
 */
public final class ArtworkHashService {
  public interface SaveArtworkHashes<I> extends Function<I, Flux<ArtworkHash>> {}
}
