package mk.ukim.mk.webaud.web.rest;

import mk.ukim.mk.webaud.model.Manufacturer;
import mk.ukim.mk.webaud.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
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
    public ResponseEntity<Manufacturer> addManufacturer(@RequestParam String name, @RequestParam String address){
        return this.manufacturerService.save(name, address)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer)) // (Optional<T>) ako se e okej se dodava vo body na response
                .orElseGet( ()-> ResponseEntity.badRequest().build()); // ()-> - supplier
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id){
        if(this.manufacturerService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
