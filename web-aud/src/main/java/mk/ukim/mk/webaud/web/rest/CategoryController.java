package mk.ukim.mk.webaud.web.rest;

import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.exceptions.CategoryAlreadyExists;
import mk.ukim.mk.webaud.model.exceptions.CategoryNotFoundException;
import mk.ukim.mk.webaud.model.exceptions.InvalidArgumentsExceptions;
import mk.ukim.mk.webaud.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<Category> findAll(){
        return this.categoryService.listCategories();
    }

    @GetMapping("/{id}")
    public Optional<Category> findById(@PathVariable Long id){
        return Optional.ofNullable(this.categoryService.findById(id));
    }

    @PostMapping("/add")
    public Category addCategory(@RequestParam String name, @RequestParam String description){
        if("".equals(name) || name==null || "".equals(description) || description==null) throw new InvalidArgumentsExceptions();

        if(this.categoryService.listCategories().stream().noneMatch(category -> category.getName().equals(name))){
            return this.categoryService.create(name,description);
        } else throw new CategoryAlreadyExists(name);
//        return this.categoryRepository.save(name, description)
//                .map(manufacturer -> ResponseEntity.ok().body(manufacturer)) // (Optional<T>) ako se e okej se dodava vo body na response
//                .orElseGet( ()-> ResponseEntity.badRequest().build()); // ()-> - supplier
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id){
        if(this.categoryService.deleteById(id)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
