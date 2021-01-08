package mk.ukim.mk.webaud.service;

import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    void deleteById(Long id);
    Optional<Product> edit(Long id, ProductDto productDto);
    Optional<Product> save(ProductDto productDto);




}
