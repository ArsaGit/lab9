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
        return itemRepository.save(itemModel);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Iterable<ItemModel> getItemList() { return itemRepository.findAll(); }

    public Optional<ItemModel> getItemById(Long id) {
        return itemRepository.findById(id);
    }
}
