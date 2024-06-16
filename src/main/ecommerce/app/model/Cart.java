package main.ecommerce.app.model;

import main.ecommerce.app.enums.CartStatus;
import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private int id;
    private User user;
    private CartStatus status; // Either 'ACTIVE' or 'COMPLETED'
    private List<Object> items;

    public Cart() {}
    public Cart(User user, String status) {
        this.user = user;
        this.status = CartStatus.valueOf(status);
    }
}
