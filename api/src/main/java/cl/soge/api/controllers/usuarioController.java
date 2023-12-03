package cl.soge.api.controllers;
import cl.soge.api.services.usuarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class usuarioController {
    @Autowired
    usuarioServices usuarioServices;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> jsonMap){
        try {
            Map<String, Object> usuarioInfo = usuarioServices.login((String) jsonMap.get("rut"), (String) jsonMap.get("contraseña"));
            if (usuarioInfo != null) {
                return ResponseEntity.status(HttpStatus.OK).body(usuarioInfo);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception error) {
            return (ResponseEntity<Map<String, Object>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }
}
