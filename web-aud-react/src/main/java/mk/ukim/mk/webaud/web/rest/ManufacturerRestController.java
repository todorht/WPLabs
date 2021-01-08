package mk.ukim.mk.webaud.web.rest;

import mk.ukim.mk.webaud.model.Manufacturer;

import mk.ukim.mk.webaud.model.dto.ManufacturerDto;

import mk.ukim.mk.webaud.model.exceptions.ManufacturerIsUsedException;
import mk.ukim.mk.webaud.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/manufacturers")
public class ManufacturerRestController {

    private final ManufacturerService manufacturerService;

    public ManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Manufacturer> findById(@PathVariable Long id){
        return this.manufacturerService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerDto manufacturerDto){
        return this.manufacturerService.save(manufacturerDto)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer)) // (Optional<T>) ako se e okej se dodava vo body na response
                .orElseGet( ()-> ResponseEntity.badRequest().build()); // ()-> - supplier
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Manufacturer> save(@PathVariable Long id, @RequestBody ManufacturerDto manufacturerDto) {
        return this.manufacturerService.edit(id, manufacturerDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id){
        if(this.manufacturerService.deleteById(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();




    }
}
