package mk.ukim.mk.webaud.repository.jpa;

import mk.ukim.mk.webaud.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    void deleteByName(String name);
}
