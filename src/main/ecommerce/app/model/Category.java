package main.ecommerce.app.model;

import lombok.Data;

@Data
public class Category {

    private int id;
    private String name;
    private String description;

    public Category() {}
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
