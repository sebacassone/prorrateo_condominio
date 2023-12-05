package cl.soge.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.soge.api.models.categoriaModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface categoriaRepository extends JpaRepository<categoriaModel, Integer> {
    Optional<categoriaModel> findByNombreCategoria(String nombreCategoria);
}
