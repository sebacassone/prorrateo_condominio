package cl.soge.api.repositories;

import cl.soge.api.models.usuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioModel, String> {
    @Query(value = "SELECT * FROM usuario WHERE usuario.rut = :rut AND usuario.contraseña = :contraseña", nativeQuery = true)
    usuarioModel loginParaUsuario(@Param("rut") String rut, @Param("contraseña") String contraseña);

    @Query(value = "SELECT ed.id_edificio, pr.id_propiedad, us.rut, us.tipo_usuario " +
            "FROM usuario us " +
            "JOIN usuario_propiedad up ON us.rut = up.rut " +
            "JOIN Propiedad pr ON up.numero_departamento = pr.id_propiedad " +
            "JOIN Edificio ed ON pr.id_edificio = ed.id_edificio " +
            "WHERE us.rut = :rut", nativeQuery = true)
    List<Object[]> buscarPropiedadesPorUsuario(@Param("rut") String rut);

}
