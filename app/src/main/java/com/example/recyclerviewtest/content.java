package com.example.recyclerviewtest;

public class content {
    String item;
    String price;
    int image;

    public content() {
    }

    public content(String item, String price, int image) {
        this.item = item;
        this.price = price;
        this.image = image;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
