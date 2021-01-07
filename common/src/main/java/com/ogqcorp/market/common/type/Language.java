package com.ogqcorp.market.common.type;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 3:51 오후 Last Modified At: 2020/12/08
 */
public enum Language {
  EN("EN", "en"),
  KO("KO", "ko"),
  JP("JP", "jp"),
  RU("RU", "ru"),
  IT("IT", "it"),
  PT("PT", "pt"),
  DE("DE", "de"),
  IR("IR", "ir"),
  FR("FR", "fr"),
  IN("IN", "in"),
  UA("UA", "ua");

  private List<String> values;
  Language(String ...values) {
    this.values = Arrays.asList(values);
  }

  @Override
  public String toString() {
    return values.stream().findFirst().orElseGet(() -> "null").toUpperCase(Locale.ROOT);
  }
}