package mk.ukim.mk.webaud.service;

import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductInShoppingCart(String username, Long productId);

}
