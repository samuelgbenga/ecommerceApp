package main.ecommerce.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.ecommerce.app.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private List<Cart> carts;
    private List<Like> likes;

    public User() {}
    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

}
