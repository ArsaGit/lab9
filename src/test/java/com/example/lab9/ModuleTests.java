package com.example.lab9;

import com.example.lab9.model.ItemModel;
import com.example.lab9.repository.ItemRepository;
import com.example.lab9.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleTests {
    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void addItem() {
        String itemName = "Item123";

        ItemModel savedItem = itemService.save(itemName);

        Assert.assertNotNull(savedItem);
        Assert.assertEquals(itemName, savedItem.getName());

        Mockito.verify(itemRepository, Mockito.times(1)).save(savedItem);
    }

    @Test
    public void deleteProduct() {
        ItemModel itemModel = new ItemModel("item2");
        Optional<ItemModel> optionalItemModel = Optional.of(itemModel);
        Mockito.when(itemRepository.findById(itemModel.getId())).thenReturn(optionalItemModel);

        itemService.delete(itemModel.getId());

        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(itemModel.getId());
    }
}
