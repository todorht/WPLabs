package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    List<Balloon> balloons = new ArrayList<>(10);

    private final ManufacturerRepository manufacturerRepository;

    public BalloonRepository(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    @PostConstruct
    public void init(){
        balloons.add(new Balloon("Srekjen balon","Srekjna nasmevka",
                this.manufacturerRepository.manufacturers.get(0)));
        balloons.add(new Balloon("Tazen balon","Srekjna nasmevka",
                this.manufacturerRepository.manufacturers.get(1)));
        balloons.add(new Balloon("Srekjen balon","Srekjna nasmevka",
                this.manufacturerRepository.manufacturers.get(2)));
        balloons.add(new Balloon("Glup balon","Srekjna nasmevka",
                this.manufacturerRepository.manufacturers.get(3)));
        balloons.add(new Balloon("Ubav balon","Srekjna nasmevka",
                this.manufacturerRepository.manufacturers.get(4)));
    }

    public List<Balloon> findAllBalloons(){
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return balloons.stream().filter(r->r.getName().contains(text) ||
                r.getDescription().contains(text)).collect(Collectors.toList());
    }
}
