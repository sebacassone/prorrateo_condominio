package cl.soge.api.repositories;

import cl.soge.api.models.edificioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface edificioRepository extends JpaRepository<edificioModel, Integer> {
}
