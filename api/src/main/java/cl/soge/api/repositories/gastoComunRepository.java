package cl.soge.api.repositories;

import cl.soge.api.models.gastoComunModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface gastoComunRepository extends JpaRepository<gastoComunModel, Integer> {
    @Query(value =
            "SELECT SUM(gc.monto_gasto), p.tamaño_inmueble, ed.tamaño_edificio FROM gasto_comun gc " +
                    "INNER JOIN edificio ed ON gc.id_edificio = ed.id_edificio " +
                    "INNER JOIN propiedad p ON ed.id_edificio = p.id_edificio " +
                    "WHERE p.numero_departamento = :numero_depto AND ed.id_edificio = :id_edificio " +
                    "GROUP BY p.tamaño_inmueble, ed.tamaño_edificio",
            nativeQuery = true
    )
    List<Object[]> obtenerDatosParaProrrateo(@Param("numero_depto") Integer numero_depto, @Param("id_edificio") Integer id_edificio);
}

