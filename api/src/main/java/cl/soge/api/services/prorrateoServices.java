package cl.soge.api.services;

import cl.soge.api.models.propiedadModel;
import cl.soge.api.models.prorrateoModel;
import cl.soge.api.models.edificioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.repositories.propiedadRepository;
import cl.soge.api.repositories.prorrateoRepository;
import cl.soge.api.repositories.gastoComunRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class prorrateoServices {

    @Autowired
    private prorrateoRepository prorrateoRepository;
    @Autowired
    private propiedadRepository propiedadRepository;
    @Autowired
    private gastoComunRepository gastoComunRepository;

    private Boolean validarFecha(String fecha) {
        try {
            // Si el mes tiene una fecha valida (YYYY-MM)
            if (!fecha.matches("^\\d{4}-\\d{2}$")) {
                return false;
            }
            // Extraer el mes y el año de la fecha
            Integer mesInt = Integer.parseInt(fecha.split("-")[1]);
            Integer añoInt = Integer.parseInt(fecha.split("-")[0]);
            // Si el mes es valido (1-12)
            if (mesInt < 1 || mesInt > 12) {
                return false;
            }
            // Si el año es valido (2021-9999)
            // Año actual
            int añoActual = Calendar.getInstance().get(Calendar.YEAR);
            if (añoInt < 1900 || añoInt > añoActual) {
                return false;
            }
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    private Integer calcularMontoProrrateo(Object[] datos) {
        try {
            // Se obtienen los datos
            Integer gastosComunes = (Integer) datos[0];
            Integer metrosCuadradosDepartamento = (Integer) datos[1];
            Integer metrosCuadradosEdificio = (Integer) datos[2];
            // Se calcula el monto del prorrateo
            Integer montoProrrateo = (metrosCuadradosDepartamento * gastosComunes) / metrosCuadradosEdificio;
            return montoProrrateo;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    private Integer crearProrrateo(Integer idEdificio, Integer numeroDepto, String fecha) {
        try {
            // Se transforma un string a fecha
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = formato.parse(fecha + "-01");
            // Se obtiene la propiedad
            propiedadModel propiedad = propiedadRepository.findById(numeroDepto)
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + numeroDepto));
            // Se obtiene los m2 del departamento, m2 del edificio, y gastos comunes del edificio. Luego se calcula el monto del prorrateo
            Integer prorrateoMonto = calcularMontoProrrateo(gastoComunRepository.obtenerDatosParaProrrateo(numeroDepto, idEdificio));
            // Se crea un nuevo prorrateo
            prorrateoModel prorrateo = new prorrateoModel();
            prorrateo.setPropiedad(propiedad);
            prorrateo.setMesAñoProrrateo(fechaDate);
            prorrateo.setMontoProrrateo(prorrateoMonto);
            prorrateoRepository.save(prorrateo);
            return prorrateoMonto;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    public Integer prorrateo(Integer idEdificio, Integer numeroDepto, String fecha) {
        try {
            if (!validarFecha(fecha)) {
                return null;
            }
            // Extraer el mes y el año de la fecha
            Integer mesInt = Integer.parseInt(fecha.split("-")[1]);
            Integer añoInt = Integer.parseInt(fecha.split("-")[0]);
            // Se hace la consulta a la base de datos para verificar si existe un prorrateo para el mes y año indicado
            Integer prorrateoMonto = prorrateoRepository.verificarProrrateoMes(numeroDepto, mesInt, añoInt);
            // Si no existe un prorrateo para el mes y año indicado
            if (prorrateoMonto == null) {
                // Se crea un nuevo prorrateo
                prorrateoMonto = crearProrrateo(idEdificio, numeroDepto, fecha);
            }
            // Se retorna el prorrateo
            return prorrateoMonto;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }
}
