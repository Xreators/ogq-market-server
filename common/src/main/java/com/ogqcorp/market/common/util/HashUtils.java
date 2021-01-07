package com.ogqcorp.market.common.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:02 오후 Last Modified At: 2020/12/17
 */
public final class HashUtils {
  /**
   * 파일 스트림의 해시값 추출
   * @param inputStream 파일의 스트림
   * @return 64자 해시값 문자열
   */
  public static String getSha256Hex(InputStream inputStream) throws IOException {
    return DigestUtils.sha256Hex(inputStream);
  }

  /**
   * 문자열의 해시값 추출
   * @param content 문자열
   * @return 64자 문자열 해시값
   */
  public static String getSha256Hex(String content) {
    return DigestUtils.sha256Hex(content);
  }

}
