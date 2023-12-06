package cl.soge.api.repositories;

import cl.soge.api.models.prorrateoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface prorrateoRepository extends JpaRepository<prorrateoModel, Integer> {
    @Query(value =
            "SELECT p.monto_prorrateo, p.fecha_vencimiento, p.mes_año_prorrateo, p.id_prorrateo FROM prorrateo p " +
                    "INNER JOIN propiedad prop ON prop.numero_departamento = p.numero_departamento " +
                    "WHERE EXTRACT(MONTH FROM p.mes_año_prorrateo) = :mes AND EXTRACT(YEAR FROM p.mes_año_prorrateo) = :año " +
                    "AND p.numero_departamento = :numero_depto AND prop.id_edificio = :id_edificio"
            , nativeQuery = true)
    List<Object[]> verificarProrrateoMes(
            @Param("numero_depto") Integer numero_depto,
            @Param("mes") Integer mes,
            @Param("año") Integer año,
            @Param("id_edificio") Integer id_edificio
    );

    @Transactional
    @Modifying
    @Query("UPDATE prorrateoModel p SET p.montoProrrateo = :montoProrrateo WHERE p.idProrrateo = :idProrrateo")
    void updateMontoProrrateoById(@Param("idProrrateo") Integer idProrrateo, @Param("montoProrrateo") Integer montoProrrateo);
}
