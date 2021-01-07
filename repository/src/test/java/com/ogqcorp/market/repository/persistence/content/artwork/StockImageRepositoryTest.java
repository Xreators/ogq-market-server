package com.ogqcorp.market.repository.persistence.content.artwork;

import static com.ogqcorp.market.common.constants.TransactionManager.CONTENT_TRANSACTION_MANAGER;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.ogqcorp.market.common.type.ImageSize;
import com.ogqcorp.market.common.type.ImageUrl;
import com.ogqcorp.market.common.type.Language;
import com.ogqcorp.market.common.type.artwork.Tag;
import com.ogqcorp.market.common.type.artwork.TextContent;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.artwork.stockimage.StockImage;
import com.ogqcorp.market.repository.config.DefaultConfig;
import com.ogqcorp.market.repository.config.TestContentJpaConfig;
import com.ogqcorp.market.repository.config.TestContentJpaConfig.TestContentRepoConfig;
import com.ogqcorp.market.repository.config.TestDataSources;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:58 오후 Last Modified At: 2020/12/09
 */

@Profile(value = {"dev","test"})
@ActiveProfiles("test")
@DataJpaTest
@Import({
  DefaultConfig.class,
  TestContentJpaConfig.class, TestContentRepoConfig.class, TestDataSources.class
})
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(transactionManager = CONTENT_TRANSACTION_MANAGER)
@Disabled("StockImageRepository Test 비활성화")
@TestConstructor(autowireMode = AutowireMode.ALL)
public class StockImageRepositoryTest {
  private Logger logger = LoggerFactory.getLogger(StockImageRepositoryTest.class);

  private StockImageRepository stockImageRepository;

  public StockImageRepositoryTest(
      StockImageRepository stockImageRepository) {
    this.stockImageRepository = stockImageRepository;
  }

  @Test
  public void save__TEST() {
    ArtworkId artworkId = ArtworkId.fromString("1234");
    CreatorId creatorId = CreatorId.fromString("5698da40ee670");
    List<Tag> tags = Arrays.asList(Tag.fromString("tag1"));
    Map<Language, TextContent> textContents = new HashMap<>();
    textContents.put(Language.EN, TextContent.builder().title("test image").description("This is a text image").build());
    StockImage expected = new StockImage(
      artworkId, creatorId,
      tags, textContents,
      ImageUrl.fromString("test/image.png"), ImageSize.builder().width(1920).height(1080).build(),
      true
    );

    StockImage saved = stockImageRepository.save(expected);

    logger.debug("{}", expected);
    logger.debug("{}", saved);
    Assertions.assertEquals(expected.artworkId(), saved.artworkId());
  }

}
