package com.example.ch08.setofstrings;

import com.example.ch08.entity.Image;
import com.example.ch08.entity.Item;
import com.example.ch08.repositories.setofstrings.ItemRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@SpringBootTest
@Transactional
class MappingCollectionsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {
        Item item = new Item();
        item.addImage("background.jpg");
        item.addImage("foreground.jpg");
        item.addImage("portrait.jpg");
        item.addImage("landscape.jpg");
        itemRepository.save(item);

        Item findItem = itemRepository.findItemWithImages(item.getId());
        SortedSet<Image> findItemImages = findItem.getImages();

        List<Item> repositoryItems = itemRepository.findAll();
        Set<String> repositoryItemImages = itemRepository.findImagesNative(findItem.getId());

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findItem).isNotNull();
        softAssertions.assertThat(findItemImages.first().getFilename()).isEqualTo("background.jpg");
        softAssertions.assertThat(findItemImages.last().getFilename()).isEqualTo("portrait.jpg");

        softAssertions.assertThat(repositoryItemImages).isNotEmpty();
        softAssertions.assertThat(repositoryItems).hasSize(1);
        softAssertions.assertThat(repositoryItems.get(0)).isEqualTo(findItem);
        softAssertions.assertThat(repositoryItemImages).hasSize(4);

        softAssertions.assertThat(repositoryItemImages).containsAll(findItemImages.stream().map(Image::getFilename).toList());
        softAssertions.assertAll();
    }
}