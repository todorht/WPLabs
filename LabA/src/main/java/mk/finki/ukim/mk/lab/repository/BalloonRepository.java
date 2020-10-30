package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    List<Balloon> balloons = new ArrayList<>(10);

    @PostConstruct
    public void init(){
        balloons.add(new Balloon("Srekjen balon","Srekjna nasmevka"));
        balloons.add(new Balloon("Tazen balon","Tazna faca"));
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
//        balloons.add(new Balloon());
    }

    public List<Balloon> findAllBalloons(){
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return balloons.stream().filter(r->r.getName().contains(text) ||
                r.getDescription().contains(text)).collect(Collectors.toList());
    }
}
