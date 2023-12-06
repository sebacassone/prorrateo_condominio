package cl.soge.api.repositories;

import cl.soge.api.models.prorrateoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface prorrateoRepository extends JpaRepository<prorrateoModel, Integer> {
    @Query(value =
            "SELECT p.monto_prorrateo, p.fecha_vencimiento, p.mes_año_prorrateo FROM prorrateo p " +
                    "WHERE EXTRACT(MONTH FROM p.mes_año_prorrateo) = :mes AND EXTRACT(YEAR FROM p.mes_año_prorrateo) = :año " +
                    "AND p.numero_departamento = :numero_depto"
            , nativeQuery = true)
    List<Object[]> verificarProrrateoMes(@Param("numero_depto") Integer numero_depto, @Param("mes") Integer mes, @Param("año") Integer año);
}
