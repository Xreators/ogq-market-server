package com.ogqcorp.market.domain.content.artwork.stockimage;

import com.ogqcorp.market.common.type.ImageSize;
import com.ogqcorp.market.common.type.ImageUrl;
import com.ogqcorp.market.common.type.Language;
import com.ogqcorp.market.common.type.artwork.ArtworkType;
import com.ogqcorp.market.common.type.artwork.Tag;
import com.ogqcorp.market.common.type.artwork.TextContent;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.artwork.Artwork;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 4:03 오후 Last Modified At: 2020/12/09
 */
@Entity
@DiscriminatorValue(value = "STOCK_IMAGE")
public class StockImage extends Artwork {

  @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private ImageExtInfo extInfo;

  public StockImage() {}
  public StockImage(
    ArtworkId artworkId, CreatorId creatorId,
    List<Tag> tags, Map<Language, TextContent> textContents,
    ImageUrl url, ImageSize imageSize, boolean createCertification
  ) {
    super(artworkId, ArtworkType.STOCK_IMAGE, creatorId, tags, textContents, createCertification);
    this.extInfo = new ImageExtInfo(this, url, imageSize);
  }

  public String imageLocation(String endPoint) {
    return extInfo.location(endPoint);
  }
}
