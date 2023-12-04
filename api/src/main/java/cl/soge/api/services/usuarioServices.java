package cl.soge.api.services;
import cl.soge.api.repositories.usuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;


@Service
public class usuarioServices {
    @Autowired
    usuarioRepository usuarioRepository;

    public Map<String, Object> infoUsuario(String rut, String password) {
        List<Object[]> resultados = usuarioRepository.buscarPropiedadesPorUsuario(rut, password);

        if (resultados != null && !resultados.isEmpty()) {
            Map<String, Object> respuesta = new HashMap<>();
            List<Integer> idsPropiedades = new ArrayList<>();

            /* Se debe modificar a futuro, no puede asumir que es solo un edificio */
            // Asumiendo que hay solo un edificio, se toma el id del edificio de la primera fila
            Integer idEdificio = (Integer) resultados.get(0)[0]; // Cambiar el índice si es necesario

            // Iterar sobre cada fila de resultados y recopilar la información de las propiedades
            for (Object[] fila : resultados) {
                idsPropiedades.add((Integer) fila[1]); // Cambiar el índice si es necesario
            }

            // Asumiendo que el nombre y tipo de usuario son iguales para todas las filas
            respuesta.put("nombre", resultados.get(0)[2]); // Cambiar el índice si es necesario
            respuesta.put("tipoUsuario", resultados.get(0)[3]); // Cambiar el índice si es necesario
            respuesta.put("idEdificio", idEdificio);
            respuesta.put("idPropiedades", idsPropiedades);

            return respuesta;
        } else {
            return null;
        }
    }
}
