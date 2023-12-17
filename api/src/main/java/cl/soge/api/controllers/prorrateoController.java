package cl.soge.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.soge.api.services.prorrateoServices;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class prorrateoController {
    @Autowired
    private prorrateoServices prorrateoServices;

    @GetMapping("/prorrateo/{id_edificio}/{numero_depto}/{mes}")
    /**
     * Obtiene el prorrateo de un departamento
     * @param id_edificio - id del edificio
     * @param numero_depto - número del departamento
     * @param mes - mes del prorrateo
     * @return
     */
    public ResponseEntity<Map<String, Object>> prorrateo(@PathVariable("id_edificio") int id_edificio, @PathVariable("numero_depto") int numero_depto, @PathVariable("mes") String mes) {
        try {
            // Formato de fecha: YYYY-MM
            Map<String, Object> prorrateo = prorrateoServices.prorrateo(id_edificio, numero_depto, mes);
            if (prorrateo != null) {
                // Si hay resultados, se retornan
                return ResponseEntity.ok(prorrateo);
            } else {
                // Si no hay resultados, se retorna un error 404
                return ResponseEntity.notFound().build();
            }
        } catch (Exception error) {
            error.printStackTrace();
            // Si hubo un error, se retorna un error 500
            return ResponseEntity.internalServerError().build();
        }
    }
}
