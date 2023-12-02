package cl.soge.api.services;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.models.usuarioModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class usuarioServices {
    @Autowired
    usuarioRepository usuarioRepository;

    public ResponseEntity<Boolean> login(String rut, String contraseña){
        ResponseEntity<Boolean> respuesta;
        usuarioModel usuario = usuarioRepository.loginParaUsuario(rut, contraseña);
        if(usuario != null){
            respuesta = ResponseEntity.ok(true);
        } else {
            respuesta = ResponseEntity.ok(false);
        }
        return respuesta;
    }
}
