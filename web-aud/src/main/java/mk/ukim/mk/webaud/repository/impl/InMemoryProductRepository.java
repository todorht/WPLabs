package mk.ukim.mk.webaud.repository.impl;

import mk.ukim.mk.webaud.bootstrap.DataHolder;
import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll(){
        return DataHolder.products;
    }

    public Product findById(Long id){
        return DataHolder.products.stream()
                .filter(p->p.getId().equals(id))
                .findFirst().orElseThrow(()->new ProductNotFoundException(id));
    }

    public Product findByName(String name){
        return DataHolder.products.stream()
                .filter(p->p.getName().equals(name))
                .findFirst().orElse(null);
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer){
        DataHolder.products.removeIf(p->p.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(p->p.getId().equals(id));
    }
}
