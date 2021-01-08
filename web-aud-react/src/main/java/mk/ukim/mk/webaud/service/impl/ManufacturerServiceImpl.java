package mk.ukim.mk.webaud.service.impl;

import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.model.dto.ManufacturerDto;
import mk.ukim.mk.webaud.model.exceptions.ManufacturerIsUsedException;
import mk.ukim.mk.webaud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.mk.webaud.repository.jpa.ManufacturerRepository;
import mk.ukim.mk.webaud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(name,address)));
    }

    @Override
    public boolean deleteById(Long id) {
            this.manufacturerRepository.deleteById(id);
        return this.manufacturerRepository.findById(id).isEmpty();
    }

    @Override
    public Optional<Manufacturer> edit(Long id, ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(id).orElseThrow(() ->new ManufacturerNotFoundException(id));
        manufacturer.setName(manufacturerDto.getName());
        manufacturer.setAddress(manufacturerDto.getAddress());

        return Optional.of(this.manufacturerRepository.save(manufacturer));
    }

    @Override
    public Optional<Manufacturer> save(ManufacturerDto manufacturerDto) {
        this.manufacturerRepository.deleteByName(manufacturerDto.getName());
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(manufacturerDto.getName(),manufacturerDto.getAddress())));
    }
}
