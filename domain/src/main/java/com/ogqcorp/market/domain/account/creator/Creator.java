package com.ogqcorp.market.domain.account.creator;

import com.ogqcorp.market.common.type.creator.CreatorStatus;
import com.ogqcorp.market.common.type.creator.SellerType;
import com.ogqcorp.market.domain.account.Email;
import com.ogqcorp.market.domain.account.NickName;
import com.ogqcorp.market.domain.account.ProfileUrl;
import com.ogqcorp.market.domain.account.UserName;
import java.time.ZonedDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:12 오후 Last Modified At: 2020/12/09
 */
@Entity
@Table(name = "creators", indexes = {
  @Index(columnList = "creator_uid", unique = true),
  @Index(columnList = "email", unique = true),
  @Index(columnList = "username", unique = true),
  @Index(columnList = "nickname", unique = true),
})
public class Creator {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "creator_uid", unique = true, length = 40))
  private CreatorId creatorId;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true, length = 255))
  private Email email;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "username", nullable = false, unique = true, length = 80))
  private UserName userName;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "nickname", nullable = false, unique = true, length = 80))
  private NickName nickName;

  @Enumerated(EnumType.STRING)
  @Column(length = 10, nullable = false)
  private CreatorStatus status;

  @Enumerated(EnumType.STRING)
  @Column(length = 10, nullable = false)
  private SellerType sellerType;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "profile_url", unique = true, length = 2039))
  private ProfileUrl profileUrl;

  private ZonedDateTime joinedAt;
  private ZonedDateTime updatedAt;

  public Creator() {}

  public CreatorId creatorId() {
    return creatorId;
  }

  public Email email() {
    return email;
  }

  public UserName userName() {
    return userName;
  }

  public NickName nickName() {
    return nickName;
  }

  public CreatorStatus creatorStatus() {
    return status;
  }

  public SellerType sellerType() {
    return sellerType;
  }

  public ProfileUrl profileUrl() {
    return profileUrl;
  }
}
