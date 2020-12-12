package mk.ukim.mk.webaud.repository.jpa;

import mk.ukim.mk.webaud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    void deleteByName(String name);
}
