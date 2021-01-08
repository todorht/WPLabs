package mk.ukim.mk.webaud.service;


import mk.ukim.mk.webaud.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> listCategories();

    Category findById(Long id);

    boolean deleteById(Long id);
}
