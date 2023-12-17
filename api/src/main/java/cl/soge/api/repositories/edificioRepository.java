package cl.soge.api.repositories;

import cl.soge.api.models.edificioModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta interfaz se encarga de realizar las consultas a la base de datos
 */
public interface edificioRepository extends JpaRepository<edificioModel, Integer> {
}
