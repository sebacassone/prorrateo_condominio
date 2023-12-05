package cl.soge.api.repositories;

import cl.soge.api.models.propiedadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface propiedadRepository extends JpaRepository<propiedadModel, Integer> {

}
