package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.exceptions.CategoryAlreadyExists;
import mk.ukim.mk.webaud.model.exceptions.CategoryNotFoundException;
import mk.ukim.mk.webaud.repository.impl.InMemoryCategoryRepository;
import mk.ukim.mk.webaud.repository.jpa.CategoryRepository;
import mk.ukim.mk.webaud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if("".equals(name) || name==null || "".equals(description) || description==null){
            throw new IllegalArgumentException();
        }
        if(this.categoryRepository.findAll().stream().noneMatch(category -> category.getName().equalsIgnoreCase(name))) {
            Category c = new Category(name, description);
            categoryRepository.save(c);
            return c;
        }else throw new CategoryAlreadyExists(name);
    }

    @Override
    public Category update(String name, String description) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException(id));
    }

    @Override
    public boolean deleteById(Long id) {
        this.categoryRepository.deleteById(id);
        if(categoryRepository.findById(id).isEmpty()) return true;
        else return false;
    }
}
