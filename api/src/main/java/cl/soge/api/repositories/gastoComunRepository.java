package cl.soge.api.repositories;

import cl.soge.api.models.gastoComunModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Esta interfaz se encarga de realizar las consultas a la base de datos
 */
public interface gastoComunRepository extends JpaRepository<gastoComunModel, Integer> {

    /*+
    * Busca los gastos comunes de un departamento en un mes y año especifico
    * @param numero_depto - numero de departamento
    * @param id_edificio - id del edificio
    * @param mes - mes
    * @param año - año
    *
    * @return
    */
    @Query(value =
            "SELECT SUM(gc.monto_gasto), p.tamaño_inmueble, ed.tamaño_edificio FROM gasto_comun gc " +
                    "INNER JOIN edificio ed ON gc.id_edificio = ed.id_edificio " +
                    "INNER JOIN propiedad p ON ed.id_edificio = p.id_edificio " +
                    "WHERE p.numero_departamento = :numero_depto AND ed.id_edificio = :id_edificio " +
                    "AND EXTRACT(MONTH FROM gc.fecha_emision) = :mes AND EXTRACT(YEAR FROM gc.fecha_emision) = :año " +
                    "GROUP BY p.tamaño_inmueble, ed.tamaño_edificio",
            nativeQuery = true
    )
    List<Object[]> obtenerDatosParaProrrateo(
            @Param("numero_depto") Integer numero_depto,
            @Param("id_edificio") Integer id_edificio,
            @Param("mes") Integer mes,
            @Param("año") Integer año
    );
}

