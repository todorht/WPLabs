package mk.ukim.mk.webaud.service;

import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.Product;
import mk.ukim.mk.webaud.model.dto.ManufacturerDto;
import mk.ukim.mk.webaud.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String address);

    boolean deleteById(Long id);

    Optional<Manufacturer> edit(Long id, ManufacturerDto manufacturerDto);
    Optional<Manufacturer> save(ManufacturerDto manufacturerDto);

}
