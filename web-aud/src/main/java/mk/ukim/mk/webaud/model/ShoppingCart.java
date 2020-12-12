package mk.ukim.mk.webaud.model;

import lombok.Data;
import mk.ukim.mk.webaud.enums.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dateCreate;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> productList;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {}

    public ShoppingCart(User user) {
        this.dateCreate = LocalDateTime.now();
        this.user = user;
        this.status = ShoppingCartStatus.CREATED;
        this.productList = new ArrayList<>();
    }
}
