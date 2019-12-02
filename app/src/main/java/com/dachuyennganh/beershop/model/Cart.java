package com.dachuyennganh.beershop.model;

public class Cart {
    private  int id;
    private  String name;
    private  int price;
    private  int amount;
    private String img;

    public Cart(int id, String name, int price, int amount, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.img = img;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
