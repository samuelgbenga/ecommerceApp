package main.ecommerce.app.model;

import lombok.Data;

@Data
public class CartItem {
    private int id;
    private int quantity;
    private int productId;
    private int cartId;


    public CartItem(){}
    public CartItem(int id, int cartId, int productId, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.cartId = cartId;
    }

}

