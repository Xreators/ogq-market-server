package com.ogqcorp.market.domain.hash;

import com.ogqcorp.market.common.util.TimeUtil;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by OGQ Corp.
 * User: iseheon
 * Created At: 2020/12/21 11:12 오전
 * Last Modified At: 2020/12/21
 *
 */
@Entity
@Table(name = "artwork_relay_error_log")
public class ArtworkRelayErrorLog {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name="artwork_id")
  private long artwork_id;

  @Column(name="message", columnDefinition = "TEXT")
  private String message;

  @Column(name="error_code")
  private int errorCode;

  @Column(name="created_at")
  private ZonedDateTime createdAt;

  public ArtworkRelayErrorLog() {}

  public ArtworkRelayErrorLog(long artwork_id, int errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
    this.artwork_id = artwork_id;
    this.createdAt = TimeUtil.utcNow();
  }

}