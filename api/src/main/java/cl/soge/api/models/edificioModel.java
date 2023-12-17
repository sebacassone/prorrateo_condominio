package cl.soge.api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Sirve para generar los getters y setters
@AllArgsConstructor // Sirve para generar un constructor con todos los argumentos
@NoArgsConstructor // Sirve para generar un constructor vacío
@Table(name = "Edificio")
/**
 * Clase que representa la tabla Edificio de la base de datos
 */
public class edificioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer idEdificio;
    @Column(name = "nombre_edificio", nullable = false)
    private String nombreEdificio;
    @Column(name = "direccion_edificio")
    private String direccionEdificio;
    @Column(name = "tamaño_edificio")
    private Integer tamañoEdificio;
    @Column(name = "cantidad_propiedades")
    private Integer cantidadPropiedades;
    @Column(name = "cantidad_ingresos")
    private Integer CantidadIngresos;
    @Column(name = "cantidad_egresos")
    private Integer CantidadEgresos;

}
