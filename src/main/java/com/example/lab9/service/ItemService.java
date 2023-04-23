package com.example.lab9.service;

import com.example.lab9.model.ItemModel;
import com.example.lab9.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemModel save(ItemModel itemModel) {
        try
        {
            itemRepository.save(itemModel);
        } catch (Exception e) { return null; }

        return itemModel;
    }

    public ItemModel save(String itemName) {
        ItemModel itemModel = new ItemModel(itemName);
        return this.save(itemModel);
    }

    public boolean delete(Long id) {
        try
        {
            itemRepository.deleteById(id);
        } catch (Exception e) { return false; }

        return true;
    }

    public Iterable<ItemModel> getItemList() { return itemRepository.findAll(); }

    public Optional<ItemModel> getItemById(Long id) {
        return itemRepository.findById(id);
    }
}
