package cl.soge.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.models.gastoComunModel;
import cl.soge.api.models.usuarioModel;
import cl.soge.api.models.edificioModel;
import cl.soge.api.repositories.gastoComunRepository;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.repositories.edificioRepository;

import java.util.Date;

@Service
public class gastoComunServices {

    @Autowired
    private gastoComunRepository gastoComunRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private edificioRepository edificioRepository;

    public boolean registrarGastoComun(String descripcionGasto, Integer montoGasto, Date fechaEmision, Integer idEdificio, String idUsuario) {
        try {
            gastoComunModel nuevoGasto = new gastoComunModel();
            nuevoGasto.setDescripcion_gasto(descripcionGasto);
            nuevoGasto.setMonto_gasto(montoGasto);
            nuevoGasto.setFecha_emision(fechaEmision);
            nuevoGasto.setFecha_registro(new Date());
            // Buscar el usuario y el edificio en la base de datos
            usuarioModel usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
            edificioModel edificio = edificioRepository.findById(idEdificio)
                    .orElseThrow(() -> new RuntimeException("Edificio no encontrado con ID: " + idEdificio));

            // Asociar el usuario y el edificio al gasto común
            nuevoGasto.setUsuario(usuario);
            nuevoGasto.setEdificio(edificio);
            // Guardar el gasto común en la base de datos
            gastoComunRepository.save(nuevoGasto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
