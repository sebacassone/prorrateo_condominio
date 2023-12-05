package cl.soge.api.repositories;

import cl.soge.api.models.prorrateoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface prorrateoRepository extends JpaRepository<prorrateoModel, Integer> {
    @Query(value =
            "SELECT p.monto FROM prorrateo p" +
                    "WHERE EXTRACT(MONTH FROM p.fecha_columna) = :mes AND EXTRACT(YEAR FROM p.fecha_columna) = :año" +
                    "AND p.numero_departamento = :numero_depto"
            , nativeQuery = true)
    Integer verificarProrrateoMes(@Param("numero_depto") Integer numero_depto, @Param("mes") Integer mes, @Param("año") Integer año);
}
