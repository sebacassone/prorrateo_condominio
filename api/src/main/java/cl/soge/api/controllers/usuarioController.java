package cl.soge.api.controllers;
import cl.soge.api.services.usuarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
/**
 * Esta clase se encarga de manejar las peticiones relacionadas con los usuarios
 */
public class usuarioController {
    @Autowired
    usuarioServices usuarioServices;

    @PostMapping("/login")
    /**
     * Obtiene la información del usuario
     * @param jsonMap - JSON con los datos del usuario
     * @return
     */
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> jsonMap) {
        try {
            // Se llama al servicio para obtener la información del usuario
            Map<String, Object> usuarioInfo = usuarioServices.obtenerInformacionUsuario((String) jsonMap.get("rut"), (String) jsonMap.get("password"));
            if (usuarioInfo != null) {
                // Si se encuentra información del usuario, se retorna
                return ResponseEntity.status(HttpStatus.OK).body(usuarioInfo);
            } else {
                // Si no se encuentra información del usuario, podría ser que no existe o no tiene propiedades
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception error) {
            // En caso de un error no manejado, devuelve INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
