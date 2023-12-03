package cl.soge.api.repositories;

import cl.soge.api.models.usuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioModel, String> {
    @Query(value = "SELECT * FROM usuario WHERE usuario.rut = :rut AND usuario.contraseña = :contraseña", nativeQuery = true)
    usuarioModel loginParaUsuario(@Param("rut") String rut, @Param("contraseña") String contraseña);
}
