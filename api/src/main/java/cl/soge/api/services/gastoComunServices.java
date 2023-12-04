package cl.soge.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.models.gastoComunModel;
import cl.soge.api.models.usuarioModel;
import cl.soge.api.models.edificioModel;
import cl.soge.api.repositories.gastoComunRepository;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.repositories.edificioRepository;

@Service
public class gastoComunServices {

    @Autowired
    private gastoComunRepository gastoComunRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private edificioRepository edificioRepository;

    public boolean registrarGastoComun(gastoComunModel gastoComun, Integer idEdificio, String idUsuario) {
        try {
            // Buscar el usuario y el edificio en la base de datos
            usuarioModel usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
            edificioModel edificio = edificioRepository.findById(idEdificio)
                    .orElseThrow(() -> new RuntimeException("Edificio no encontrado con ID: " + idEdificio));

            // Asociar el usuario y el edificio al gasto común
            gastoComun.setUsuario(usuario);
            gastoComun.setEdificio(edificio);

            // Guardar el gasto común en la base de datos
            gastoComunRepository.save(gastoComun);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
