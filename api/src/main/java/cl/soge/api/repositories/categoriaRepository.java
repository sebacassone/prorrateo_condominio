package cl.soge.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.soge.api.models.categoriaModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Esta interfaz se encarga de realizar las consultas a la base de datos
 */
public interface categoriaRepository extends JpaRepository<categoriaModel, Integer> {
    /**
     * Busca una categoria por su nombre
     * @param nombreCategoria - nombre de la categoria
     * @return
     */
    Optional<categoriaModel> findByNombreCategoria(String nombreCategoria);
}
