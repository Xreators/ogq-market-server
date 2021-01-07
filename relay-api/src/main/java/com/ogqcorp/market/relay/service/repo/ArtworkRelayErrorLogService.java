package com.ogqcorp.market.relay.service.repo;

import com.ogqcorp.market.domain.hash.ArtworkRelayErrorLog;
import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:23 오전 Last Modified At: 2020/12/21
 */
public final class ArtworkRelayErrorLogService {
  public interface SaveArtworkRelayErrorLog<I> extends Function<I, Mono<ArtworkRelayErrorLog>> {}
}
