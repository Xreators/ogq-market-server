package com.ogqcorp.market.domain.content.artwork.stockimage;

import com.ogqcorp.market.common.type.ImageSize;
import com.ogqcorp.market.common.type.ImageUrl;
import com.ogqcorp.market.domain.content.artwork.FileLocation;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 4:22 오후 Last Modified At: 2020/12/09
 */

@Entity
@Table(name = "image_ext_infos")
public class ImageExtInfo {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @OneToOne
  @JoinColumn(name = "artwork_id")
  private StockImage image;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name="image_key", length = 2039)) // ie maximum 2047
  private FileLocation imageKey;
  private int width;
  private int height;

  public ImageExtInfo() {}

  public ImageExtInfo(StockImage image, ImageUrl url, ImageSize imageSize) {
    this.image = image;
    this.imageKey = FileLocation.fromImageUrl(url);
    this.width = imageSize.getWidth();
    this.height = imageSize.getHeight();
  }
  public Boolean isPortrait() {
    return height > width;
  }
  public String location(String endPoint) {
    return imageKey.location(endPoint);
  }

  public void updateSize(ImageSize imageSize) {
    this.width = imageSize.getWidth();
    this.height = imageSize.getHeight();
  }

}
