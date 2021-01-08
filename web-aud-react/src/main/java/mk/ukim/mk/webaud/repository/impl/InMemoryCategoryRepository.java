package mk.ukim.mk.webaud.repository.impl;

import mk.ukim.mk.webaud.bootstrap.DataHolder;
import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.model.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {

    public List<Category> findAll(){
      return DataHolder.categories;
    }

    public Category save(Category c){
        if(c==null || c.getName()==null || c.getName().isEmpty() ){
            return null;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Category findById(Long id){
        return DataHolder.categories.stream()
                .filter(c->c.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new CategoryNotFoundException(id));
    }

    public Optional<Category> findByName(String name){  //ako ne najde, vrakja prazno
        return DataHolder.categories.stream().filter(r->r.getName().equals(name)).findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(r->r.getName().contains(text)
                || r.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name){
        if(name==null){
            return;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(name));
    }
}
