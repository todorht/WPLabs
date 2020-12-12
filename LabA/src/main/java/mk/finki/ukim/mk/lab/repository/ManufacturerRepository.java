package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ManufacturerRepository {

    List<Manufacturer> manufacturers = new ArrayList<>();

    @PostConstruct
    public void init(){
        manufacturers.add(new Manufacturer("India B.","India","New Delhi"));
        manufacturers.add(new Manufacturer("China B.","China","Beijing"));
        manufacturers.add(new Manufacturer("Macedonia B.","Macedonia","Skopje"));
        manufacturers.add(new Manufacturer("America B.","America","Washington, D.C."));
        manufacturers.add(new Manufacturer("Russia B","Russia","Moscow"));
    }

    public List<Manufacturer> findAll(){
        return this.manufacturers;
    }


}
