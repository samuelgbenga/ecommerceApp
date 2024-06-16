package main.ecommerce.app.model;

import lombok.Data;

@Data
public class Like {
    private int id;
    private User user;
    private Product product;

    public Like(){}
    public Like(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
