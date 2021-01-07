package com.ogqcorp.market.hash.service.storage.impl;


import com.ogqcorp.market.common.util.HashUtils;
import com.ogqcorp.market.hash.service.storage.FileHashService.GetFileSha256HashFrom;
import com.ogqcorp.market.hash.service.storage.exception.FileHashCreateFailedException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 3:07 오후 Last Modified At: 2020/12/17
 */
@Service
public class GetFileSha256HashFromPath implements GetFileSha256HashFrom<String> {
  private final Logger logger = LoggerFactory.getLogger(GetFileSha256HashFromPath.class);

  @Override
  public Mono<String> apply(String path) {
    return Mono.fromCallable(() -> Optional.ofNullable(path))
      .publishOn(Schedulers.boundedElastic())
      .filter(Optional::isPresent)
      .switchIfEmpty(Mono.error(new FileHashCreateFailedException("path is null")))
      .map(it -> getStreamFromUrl(it.get())
        .map(this::createImageHash)
        .orElseThrow(() -> new FileHashCreateFailedException("stream open failed"))
      )
      .onErrorResume(Mono::error);
  }

  private Optional<InputStream> getStreamFromUrl(String path) {
    try {
      URL url = new URL(path);
      return Optional.ofNullable(url.openStream());
    } catch (MalformedURLException e) {
      logger.error("{}", e.getMessage(), e);
      return Optional.ofNullable(null);
    } catch (IOException e) {
      logger.error("{}", e.getMessage(), e);
      return Optional.ofNullable(null);
    } catch (Exception e) {
      logger.error("{}", e.getMessage(), e);
      return Optional.ofNullable(null);
    }
  }
  private String createImageHash(InputStream is) {
    return Optional.ofNullable(is)
        .map(this::getHash)
        .orElseThrow(() -> new FileHashCreateFailedException("read error"))
        .orElseThrow(() -> new FileHashCreateFailedException("stream is null"));
  }
  private Optional<String> getHash(InputStream is) {
    try {
      return Optional.ofNullable(HashUtils.getSha256Hex(is));
    } catch (IOException e) {
      logger.error("{}", e.getMessage(), e);
      return Optional.ofNullable(null);
    }
  }
}
