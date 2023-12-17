package cl.soge.api.services;

import cl.soge.api.models.propiedadModel;
import cl.soge.api.models.prorrateoModel;
import cl.soge.api.models.edificioModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.soge.api.repositories.propiedadRepository;
import cl.soge.api.repositories.prorrateoRepository;
import cl.soge.api.repositories.gastoComunRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class prorrateoServices {

    @Autowired
    private prorrateoRepository prorrateoRepository;
    @Autowired
    private propiedadRepository propiedadRepository;
    @Autowired
    private gastoComunRepository gastoComunRepository;

    /**
     * Valida que la fecha sea válida
     *
     * @param fecha - fecha a validar
     * @return
     */
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
            if (añoInt < 1900 || añoInt > 2099) {
                return false;
            }
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    /**
     * Calcula el monto del prorrateo
     *
     * @param datos - datos para calcular el prorrateo
     * @return
     */
    private Integer calcularMontoProrrateo(List<Object[]> datos) {
        try {
            if (datos == null || datos.isEmpty()) {
                return null;
            }
            // Se obtiene los m2 del departamento, m2 del edificio, y gastos comunes del edificio
            Integer metrosCuadradosDepartamentoInt = (Integer) datos.get(0)[1];
            Integer metrosCuadradosEdificioInt = (Integer) datos.get(0)[2];
            Long gastosComunes = (Long) datos.get(0)[0];

            // Utilizar números de punto flotante para la división
            double resultado = (double) (metrosCuadradosDepartamentoInt.longValue() * gastosComunes) / metrosCuadradosEdificioInt.longValue();

            // Redondear el resultado al entero más cercano
            int resultadoRedondeado = (int) Math.round(resultado);

            // Se calcula el monto del prorrateo
            return resultadoRedondeado;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    /**
     * Calcula la fecha de vencimiento
     *
     * @param fechaDate - fecha
     * @param fecha     - fecha en string
     * @return
     * @throws ParseException
     */
    private Date fechaVencimiento(Date fechaDate, String fecha) throws ParseException {
        // Convierte la fecha a un objeto Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDate);
        // Obtén el último día del mes
        Integer ultimoDiaDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaVencimiento = formato.parse(fecha + "-" + ultimoDiaDelMes);
        return fechaVencimiento;
    }

    /**
     * Crea un nuevo prorrateo
     *
     * @param idEdificio  - id del edificio
     * @param numeroDepto - numero de departamento
     * @param fecha       - fecha
     * @return
     */
    private List<Object[]> crearProrrateo(Integer idEdificio, Integer numeroDepto, String fecha) {
        try {
            // Se transforma un string a fecha
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = formato.parse(fecha + "-01");

            // Se calcula la fecha de vencimiento
            Date fechaVencimiento = fechaVencimiento(fechaDate, fecha);

            // Se obtiene la propiedad
            propiedadModel propiedad = propiedadRepository.findById(numeroDepto)
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + numeroDepto));
            // Extraer el mes y el año de la fecha
            Integer mesInt = Integer.parseInt(fecha.split("-")[1]);
            Integer añoInt = Integer.parseInt(fecha.split("-")[0]);
            // Se obtiene los m2 del departamento, m2 del edificio, y gastos comunes del edificio. Luego se calcula el monto del prorrateo
            Integer prorrateoMonto = calcularMontoProrrateo(gastoComunRepository.obtenerDatosParaProrrateo(numeroDepto, idEdificio, mesInt, añoInt));
            if (prorrateoMonto == null) {
                return null;
            }
            // Se crea un nuevo prorrateo
            prorrateoModel prorrateo = new prorrateoModel();
            prorrateo.setPropiedad(propiedad);
            prorrateo.setCriterioProrrateo("M2");
            prorrateo.setMesAñoProrrateo(fechaDate);
            prorrateo.setMontoProrrateo(prorrateoMonto);
            prorrateo.setFechaVencimiento(fechaVencimiento);
            prorrateoRepository.save(prorrateo);

            // Se mete el monto y la fecha de vencimiento en una lista de objetos
            List<Object[]> prorrateoObjeto = new ArrayList<>();
            prorrateoObjeto.add(new Object[]{prorrateoMonto, fechaVencimiento, fechaDate});
            return prorrateoObjeto;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene el prorrateo de un departamento en un mes y año especifico
     *
     * @param idEdificio  - id del edificio
     * @param numeroDepto - numero de departamento
     * @param fecha       - fecha
     * @return
     */
    public Map<String, Object> prorrateo(Integer idEdificio, Integer numeroDepto, String fecha) {
        try {
            if (!validarFecha(fecha)) {
                return null;
            }
            // Extraer el mes y el año de la fecha
            Integer mesInt = Integer.parseInt(fecha.split("-")[1]);
            Integer añoInt = Integer.parseInt(fecha.split("-")[0]);
            // Se hace la consulta a la base de datos para verificar si existe un prorrateo para el mes y año indicado
            List<Object[]> prorrateo = prorrateoRepository.verificarProrrateoMes(numeroDepto, mesInt, añoInt, idEdificio);

            // Si no existe un prorrateo para el mes y año indicado
            if (prorrateo == null || prorrateo.isEmpty()) {
                // Se crea un nuevo prorrateo
                prorrateo = crearProrrateo(idEdificio, numeroDepto, fecha);
                if (prorrateo == null) {
                    return null;
                }
            } else {
                Integer prorrateoMonto = calcularMontoProrrateo(gastoComunRepository.obtenerDatosParaProrrateo(numeroDepto, idEdificio, mesInt, añoInt));
                if (prorrateoMonto == null) {
                    return null;
                }
                // Se actualiza el monto del prorrateo
                if (prorrateo.get(0)[0] != prorrateoMonto) {
                    prorrateo.get(0)[0] = prorrateoMonto;
                    prorrateoRepository.updateMontoProrrateoById((Integer) prorrateo.get(0)[3], prorrateoMonto);
                }
            }
            // Se retorna el prorrateo
            // Se deja un HashMap para que se pueda agregar más información en el futuro
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("montoProrrateo", prorrateo.get(0)[0]);
            respuesta.put("fecha", prorrateo.get(0)[2]);
            respuesta.put("idEdificio", idEdificio);
            respuesta.put("numeroDepto", numeroDepto);
            respuesta.put("fechaVencimiento", prorrateo.get(0)[1]);
            return respuesta;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }
}
