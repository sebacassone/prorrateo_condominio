package cl.soge.api.controllers;
import cl.soge.api.services.usuarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping
public class usuarioController {
    @Autowired
    usuarioServices usuarioServices;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String rut, @RequestParam String contraseña){
        Map<String, Object> usuarioInfo = (Map<String, Object>) usuarioServices.login(rut, contraseña);
        if(usuarioInfo != null){
            return ResponseEntity.ok(usuarioInfo.toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


}
