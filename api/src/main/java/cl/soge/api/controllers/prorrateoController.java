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

    @GetMapping("/prorrateo/{id_edificio}/{numero_depto}/{fecha}")
    public ResponseEntity<Integer> prorrateo(@PathVariable("id_edificio") int id_edificio, @PathVariable("numero_depto") int numero_depto, @PathVariable("mes") String mes) {
        try {
            // Formato de fecha: YYYY-MM
            Integer prorrateo = prorrateoServices.prorrateo(id_edificio, numero_depto, mes);
            if (prorrateo != null) {
                return ResponseEntity.ok(prorrateo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception error) {
            error.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
