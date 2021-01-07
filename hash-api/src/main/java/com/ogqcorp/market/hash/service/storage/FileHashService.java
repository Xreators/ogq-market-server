package com.ogqcorp.market.hash.service.storage;

import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 3:05 오후 Last Modified At: 2020/12/17
 */
public final class FileHashService {
  public interface GetFileSha256HashFrom<I> extends Function<I, Mono<String>> {}
}
