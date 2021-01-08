package mk.ukim.mk.webaud.repository.jpa;

import mk.ukim.mk.webaud.enums.ShoppingCartStatus;
import mk.ukim.mk.webaud.model.ShoppingCart;
import mk.ukim.mk.webaud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
