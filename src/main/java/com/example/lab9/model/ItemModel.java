package com.example.lab9.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "isBought", nullable = false)
    private Boolean isBought = false;

    public ItemModel(String name, Boolean isBought) { this.name = name; this.isBought = isBought; }

    public ItemModel(String name) { this(name, false); }

    public ItemModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBought() {
        return isBought;
    }

    public void setBought(Boolean bought) {
        isBought = bought;
    }
}
