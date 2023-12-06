package cl.soge.api.services;

import cl.soge.api.models.categoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.models.gastoComunModel;
import cl.soge.api.models.usuarioModel;
import cl.soge.api.models.edificioModel;
import cl.soge.api.repositories.gastoComunRepository;
import cl.soge.api.repositories.usuarioRepository;
import cl.soge.api.repositories.edificioRepository;
import cl.soge.api.repositories.categoriaRepository;

import java.util.Date;
import java.util.List;

@Service
public class gastoComunServices {

    @Autowired
    private gastoComunRepository gastoComunRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private edificioRepository edificioRepository;

    @Autowired
    private categoriaRepository categoriaRepository;

    public boolean registrarGastoComun(String descripcionGasto, Integer montoGasto, Date fechaEmision, Integer idEdificio, String idUsuario, String nombreCategoria) {
        try {
            gastoComunModel nuevoGasto = new gastoComunModel();
            nuevoGasto.setDescripcionGasto(descripcionGasto);
            nuevoGasto.setMontoGasto(montoGasto);
            nuevoGasto.setFechaEmision(fechaEmision);
            nuevoGasto.setFechaRegistro(new Date());

            usuarioModel usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
            edificioModel edificio = edificioRepository.findById(idEdificio)
                    .orElseThrow(() -> new RuntimeException("Edificio no encontrado con ID: " + idEdificio));

            // Aquí se busca la categoría por nombre
            categoriaModel categoria = categoriaRepository.findByNombreCategoria(nombreCategoria)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con nombre: " + nombreCategoria));

            // Se asocia la categoría al nuevo gasto
            nuevoGasto.setCategorias(List.of(categoria));

            nuevoGasto.setUsuario(usuario);
            nuevoGasto.setEdificio(edificio);

            gastoComunRepository.save(nuevoGasto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
