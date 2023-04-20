package com.example.lab9.controller;

import com.example.lab9.model.ItemModel;
import com.example.lab9.service.ItemService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public Iterable<ItemModel> itemList() { return itemService.getItemList(); }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemModel> getItemById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ItemModel item = itemService.getItemById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + id));
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemModel> createItem(@RequestBody JSONObject jsonItem) {
        ItemModel itemModel = itemService.save(new ItemModel(jsonItem.get("name").toString()));
        return ResponseEntity.ok(itemModel);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemModel> updateItem(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody ItemModel itemDetails) throws ResourceNotFoundException {
        ItemModel itemModel = itemService.getItemById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + id));

        itemModel.setBought(itemDetails.getBought());
        final ItemModel updatedItem = itemService.save(itemModel);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long id) {
        ItemModel itemModel = itemService.getItemById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + id));

        itemService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
