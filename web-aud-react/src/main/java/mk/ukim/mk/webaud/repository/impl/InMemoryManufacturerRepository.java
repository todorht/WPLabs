package mk.ukim.mk.webaud.repository.impl;

import mk.ukim.mk.webaud.bootstrap.DataHolder;
import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.exceptions.ManufacturerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }

    public Manufacturer findById(Long id){
        return DataHolder.manufacturers.stream()
                .filter(i->i.getId().equals(id))
                .findFirst().orElseThrow(()-> new ManufacturerNotFoundException(id));
    }

    public Optional<Manufacturer> save(String name, String address){
        Manufacturer manufacturer = new Manufacturer(name,address);
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }

    public boolean deleteById(Long id){
        return DataHolder.manufacturers.removeIf(r->r.getId().equals(id));
    }
}
