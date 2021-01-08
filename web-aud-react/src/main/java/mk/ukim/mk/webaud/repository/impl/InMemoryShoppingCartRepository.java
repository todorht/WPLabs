package mk.ukim.mk.webaud.repository.impl;

import mk.ukim.mk.webaud.bootstrap.DataHolder;
import mk.ukim.mk.webaud.enums.ShoppingCartStatus;
import mk.ukim.mk.webaud.model.ShoppingCart;
import mk.ukim.mk.webaud.model.exceptions.ShoppingCartNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public ShoppingCart findById(Long id){
        return DataHolder.shoppingCarts.stream().filter(s->s.getId().equals(id)).findFirst().orElseThrow(()->new ShoppingCartNotFoundException(id));
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream()
                .filter(s->s.getUser().getUsername().equals(username) && s.getStatus().equals(status))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        DataHolder.shoppingCarts
                .removeIf(r->r.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }



}
