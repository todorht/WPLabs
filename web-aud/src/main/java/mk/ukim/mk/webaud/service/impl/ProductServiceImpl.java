package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.exceptions.CategoryNotFoundException;
import mk.ukim.mk.webaud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.mk.webaud.model.exceptions.ProductNotFoundException;
import mk.ukim.mk.webaud.repository.jpa.CategoryRepository;
import mk.ukim.mk.webaud.repository.jpa.ManufacturerRepository;
import mk.ukim.mk.webaud.repository.jpa.ProductRepository;
import mk.ukim.mk.webaud.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(()-> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Product product = this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()-> new ManufacturerNotFoundException(manufacturerId));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);



        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
