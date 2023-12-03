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

    @Query(value = "SELECT u.id_edificio, p.id_propiedad, u.rut, u.tipo_usuario " +
            "FROM usuario u " +
            "JOIN Propiedad p ON u.rut = p.id_usuario " +
            "WHERE u.rut = :rut", nativeQuery = true)
    List<Object[]> buscarPropiedadesPorUsuario(@Param("rut") String rut);
}
