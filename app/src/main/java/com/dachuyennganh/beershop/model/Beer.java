package com.dachuyennganh.beershop.model;

import java.io.Serializable;

public class Beer implements Serializable {
    private int ID;
    private String name;
    private String description;
    private Integer price ;
    private String image;
    private int typebeer_id;

    public Beer(int ID, String name, Integer price, String image, String description, int typebeer_id) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.typebeer_id = typebeer_id;
    }

    public Beer() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTypebeer_id() {
        return typebeer_id;
    }

    public void setTypebeer_id(int typebeer_id) {
        this.typebeer_id = typebeer_id;
    }
}
