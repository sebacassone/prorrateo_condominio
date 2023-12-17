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

    /**
     * Obtiene la información del usuario
     * @param rut - rut del usuario
     * @param password - contraseña del usuario
     * @return
     */
    public Map<String, Object> obtenerInformacionUsuario(String rut, String password) {
        // Buscar las propiedades asociadas al usuario
        List<Object[]> resultadosPropiedades = usuarioRepository.buscarPropiedadesPorUsuario(rut, password);

        // Si hay resultados, construir la respuesta
        if (resultadosPropiedades != null && !resultadosPropiedades.isEmpty()) {
            return construirRespuesta(resultadosPropiedades);
        } else {
            // Si no hay resultados, buscar el usuario y retornar los datos del usuario
            List<Object[]> resultadosUsuario = usuarioRepository.buscarUsuario(rut, password);

            // Si hay resultados, construir la respuesta
            if (resultadosUsuario != null && !resultadosUsuario.isEmpty()) {
                return construirRespuestaUsuario(resultadosUsuario);
            } else {
                return null;
            }
        }
    }

    /**
     * Construye la respuesta con los datos del usuario y sus propiedades
     * @param resultados - resultados de la consulta
     * @return
     */
    private Map<String, Object> construirRespuesta(List<Object[]> resultados) {
        Map<String, Object> respuesta = new HashMap<>();
        List<Integer> idsPropiedades = new ArrayList<>();

        // Asumiendo que hay solo un edificio, se toma el id del edificio de la primera fila
        Integer idEdificio = (Integer) resultados.get(0)[0]; // Cambiar el índice si es necesario

        // Iterar sobre cada fila de resultados y recopilar la información de las propiedades
        for (Object[] fila : resultados) {
            idsPropiedades.add((Integer) fila[1]); // Cambiar el índice si es necesario
        }

        // Asumiendo que el nombre y tipo de usuario son iguales para todas las filas
        respuesta.put("nombre", resultados.get(0)[2]); // Cambiar el índice si es necesario
        respuesta.put("apellido", resultados.get(0)[3]); // Cambiar el índice si es necesario
        respuesta.put("tipoUsuario", resultados.get(0)[4]); // Cambiar el índice si es necesario
        respuesta.put("idEdificio", idEdificio);
        respuesta.put("idPropiedades", idsPropiedades);

        return respuesta;
    }

    /**
     * Construye la respuesta con los datos del usuario
     * @param resultados - resultados de la consulta
     * @return
     */
    private Map<String, Object> construirRespuestaUsuario(List<Object[]> resultados) {
        // Asumiendo que el nombre y tipo de usuario son iguales para todas las filas
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombre", resultados.get(0)[0]); // Cambiar el índice si es necesario
        respuesta.put("apellido", resultados.get(0)[1]); // Cambiar el índice si es necesario
        respuesta.put("tipoUsuario", resultados.get(0)[2]); // Cambiar el índice si es necesario
        respuesta.put("idEdificio", null);
        respuesta.put("idPropiedades", null);

        return respuesta;
    }
}
