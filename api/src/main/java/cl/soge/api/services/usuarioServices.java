package cl.soge.api.services;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.models.usuarioModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class usuarioServices {
    @Autowired
    usuarioRepository usuarioRepository;

    public Map<String, Object> login(String rut, String contraseña){
        usuarioModel usuario = usuarioRepository.loginParaUsuario(rut, contraseña);
        if(usuario != null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre", usuario.getNombre());
            respuesta.put("tipoUsuario", usuario.getTipoUsuario());
            return respuesta;
        } else {
            return null;
        }
    }

}
