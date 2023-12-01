package cl.soge.api.services;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.models.usuarioModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioServices {
    @Autowired
    usuarioRepository usuarioRepository;

    public Boolean login(String rut, String contraseña){
        Boolean respuesta;
        usuarioModel usuario = usuarioRepository.loginParaUsuario(rut, contraseña);
        if (usuario.getNombre() == null){
            respuesta = false;
        } else {
            respuesta = true;
        }
        return respuesta;
    }
}
