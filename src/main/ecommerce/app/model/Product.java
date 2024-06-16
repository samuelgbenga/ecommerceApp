package main.ecommerce.app.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    private String pictureUrl;

    public Product(){}

    public Product(int id, String name, String description, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(int id, String name, String description, double price, int categoryId, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.pictureUrl = pictureUrl;
    }


    public Product(String name, String description, double price, int categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(String name, String description, double price, int categoryId, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.pictureUrl = pictureUrl;
    }

}
