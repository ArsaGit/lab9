package com.example.lab9;

import com.example.lab9.controller.ItemRestController;
import com.example.lab9.model.ItemModel;
import com.example.lab9.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {
    @Autowired
    private ItemRestController itemRestController;

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private MockMvc mockMvc;

    ItemModel itemModel1 = new ItemModel("qwe1");
    ItemModel itemModel2 = new ItemModel("qwe2");
    ItemModel itemModel3 = new ItemModel("qwe3");

    @Test
    public void contextLoads() {

    }

    @Test
    public void rest() throws Exception{
        Assert.assertNotNull(itemRestController);
    }

    @Test
    public void itemList() throws Exception {
        List<ItemModel> itemList = new ArrayList<>(Arrays.asList(itemModel1, itemModel2, itemModel3));

        Mockito.when(itemRepository.findAll()).thenReturn(itemList);

        mockMvc.perform(get("/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("qwe3")));
    }

    @Test
    public void getItemById() throws Exception {
        itemModel1.setId(1l);
        Mockito.when(itemRepository.findById(itemModel1.getId())).thenReturn(Optional.of(itemModel1));

        mockMvc.perform(get("/items/" + itemModel1.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("qwe1")));
    }
}
