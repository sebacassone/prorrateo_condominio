package cl.soge.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.soge.api.models.gastoComunModel;
import cl.soge.api.services.gastoComunServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
@RestController
public class gastoComunController {

    private final gastoComunServices gastoComunServices;

    @Autowired
    public gastoComunController(gastoComunServices gastoComunServices) {
        this.gastoComunServices = gastoComunServices;
    }

    @PostMapping("/registroGastoComun")
    public ResponseEntity<Map<String, String>> registroGastoComun(@RequestBody Map<String, Object> jsonMap) {
        try {
            System.out.println("Received JSON Map: " + jsonMap);

            gastoComunModel nuevoGasto = new gastoComunModel();
            nuevoGasto.setDescripcion_gasto((String) jsonMap.get("descripcion_gasto"));
            nuevoGasto.setMonto_gasto(Integer.valueOf(jsonMap.get("monto_gasto").toString()));
            nuevoGasto.setFecha_emision(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonMap.get("fecha_emision")));
            nuevoGasto.setFecha_registro(new Date());
            nuevoGasto.setIdEdificio(Integer.valueOf(jsonMap.get("id_edificio").toString()));
            nuevoGasto.setIdUsuario(Integer.valueOf((String) jsonMap.get("id_usuario")));

            boolean result = this.gastoComunServices.registrarGastoComun(nuevoGasto);
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Gasto común registrado con éxito"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Error al registrar el gasto común"));
            }
        } catch (Exception error) {
            error.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error interno del servidor: " + error.getMessage()));
        }
    }
}
