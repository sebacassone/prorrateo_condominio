package cl.soge.api.repositories;

import cl.soge.api.models.propiedadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Esta interfaz se encarga de realizar las consultas a la base de datos
 */
public interface propiedadRepository extends JpaRepository<propiedadModel, Integer> {

}
