package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.enums.ShoppingCartStatus;
import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.ShoppingCart;
import mk.ukim.mk.webaud.model.User;
import mk.ukim.mk.webaud.model.exceptions.ProductAlreadyInShoppingCart;
import mk.ukim.mk.webaud.model.exceptions.ProductNotFoundException;
import mk.ukim.mk.webaud.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.mk.webaud.model.exceptions.UserNotFoundException;
import mk.ukim.mk.webaud.repository.impl.InMemoryProductRepository;
import mk.ukim.mk.webaud.repository.impl.InMemoryShoppingCartRepository;
import mk.ukim.mk.webaud.repository.impl.InMemoryUserRepository;
import mk.ukim.mk.webaud.repository.jpa.ProductRepository;
import mk.ukim.mk.webaud.repository.jpa.ShoppingCartRepository;
import mk.ukim.mk.webaud.repository.jpa.UserRepository;
import mk.ukim.mk.webaud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProductList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user,ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                    ShoppingCart newShoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(newShoppingCart);
                });

    }

    @Override
    public ShoppingCart addProductInShoppingCart(String username, Long productId) {
       ShoppingCart shoppingCart = this.getActiveShoppingCart(username);

       Product product = this.productRepository.findById(productId)
               .orElseThrow(()->new ProductNotFoundException(productId));

        if(shoppingCart.getProductList().stream().anyMatch(p -> p.getId().equals(productId)))
            throw new ProductAlreadyInShoppingCart(productId,username);
       shoppingCart.getProductList().add(product);
       return this.shoppingCartRepository.save(shoppingCart);
    }
}
