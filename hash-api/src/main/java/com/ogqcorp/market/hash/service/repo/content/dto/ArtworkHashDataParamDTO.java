package com.ogqcorp.market.hash.service.repo.content.dto;

import com.ogqcorp.market.common.type.Language;
import java.util.Map;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 1:25 오후 Last Modified At: 2020/12/17
 */
public class ArtworkHashDataParamDTO {
  private String creatorId;
  private String artworkId;
  private String uciCode;
  private Map<Language, String> titles;
  private long dataInsertedAt;
  private String fileHash;

  private ArtworkHashDataParamDTO() {}
  public static Builder builder() {
    return new ArtworkHashDataParamDTO.Builder();
  }

  public String getCreatorId() {
    return creatorId;
  }

  public String getArtworkId() {
    return artworkId;
  }

  public String getUciCode() {
    return uciCode;
  }

  public Map<Language, String> getTitles() {
    return titles;
  }

  public long getDataInsertedAt() {
    return dataInsertedAt;
  }

  public String getFileHash() {
    return fileHash;
  }

  public static class Builder {
    private ArtworkHashDataParamDTO data;
    private Builder() {
      this.data = new ArtworkHashDataParamDTO();
    }
    public Builder creatorId(String creatorId) {
      data.creatorId = creatorId;
      return this;
    }
    public Builder artworkId(String artworkId) {
      data.artworkId = artworkId;
      return this;
    }
    public Builder uciCode(String uciCode) {
      data.uciCode = uciCode;
      return this;
    }
    public Builder titles(Map<Language, String> titles) {
      data.titles = titles;
      return this;
    }
    public Builder fileHash(String fileHash) {
      data.fileHash = fileHash;
      return this;
    }
    public Builder dataInsertedAt(long dataInsertedAt) {
      data.dataInsertedAt = dataInsertedAt;
      return this;
    }
    public ArtworkHashDataParamDTO build() {
      return data;
    }
  }

}
