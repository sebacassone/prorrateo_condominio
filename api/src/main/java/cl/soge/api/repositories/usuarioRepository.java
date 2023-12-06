package cl.soge.api.repositories;

import cl.soge.api.models.usuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioModel, String> {
    @Query(value = "SELECT ed.id_edificio, pr.numero_departamento, us.nombre, us.apellido, us.tipo_usuario " +
            "FROM usuario us " +
            "JOIN usuario_propiedad up ON us.rut = up.rut " +
            "JOIN Propiedad pr ON up.numero_departamento = pr.numero_departamento " +
            "JOIN Edificio ed ON pr.id_edificio = ed.id_edificio " +
            "WHERE us.rut = :rut AND us.password = :password", nativeQuery = true)
    List<Object[]> buscarPropiedadesPorUsuario(@Param("rut") String rut, @Param("password") String password);

    @Query(value = "SELECT us.nombre, us.apellido, us.tipo_usuario " +
            "FROM usuario us " +
            "WHERE us.rut = :rut AND us.password = :password", nativeQuery = true)
    List<Object[]> buscarUsuario(@Param("rut") String rut, @Param("password") String password);
}
