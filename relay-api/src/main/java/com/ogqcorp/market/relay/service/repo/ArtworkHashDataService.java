package com.ogqcorp.market.relay.service.repo;

import com.ogqcorp.market.domain.hash.ArtworkHashData;
import java.util.List;
import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 10:31 오전 Last Modified At: 2020/12/21
 */
public final class ArtworkHashDataService {
  public interface GetRelayReqListByIds<I> extends Function<I, Mono<List<ArtworkHashData>>>{}
  public interface UpdateArtworkHashDataTo<I> extends Function<I, Mono<ArtworkHashData>>{}
}
