package mk.ukim.mk.webaud.web.controller;

import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.exceptions.ProductNotFoundException;
import mk.ukim.mk.webaud.service.CategoryService;
import mk.ukim.mk.webaud.service.ManufacturerService;
import mk.ukim.mk.webaud.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model){
       if(error!=null && !error.isEmpty()) {
           model.addAttribute("hasError", true);
           model.addAttribute("error",error);
       }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //koj smee da pristapi
    public String addProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent","add-product");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.productService.findById(id)!=null) {
            Product product = this.productService.findById(id).orElseThrow(()->new ProductNotFoundException(id));
            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent","add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveProduct(@RequestParam String name,@RequestParam Double price, @RequestParam Integer quantity,
                              @RequestParam Long category, @RequestParam Long manufacturer){
        this.productService.save(name, price, quantity, category, manufacturer);
        return "redirect:/products";
    }

}
