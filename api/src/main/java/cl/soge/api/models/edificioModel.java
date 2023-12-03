package cl.soge.api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Edificio")
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
    @Column(name = "cantidad_pisos")
    private Integer cantidadPisos;
    @Column(name = "cantidad_ingresos")
    private Integer CantidadIngresos;
    @Column(name = "cantidad_egresos")
    private Integer CantidadEgresos;

}
