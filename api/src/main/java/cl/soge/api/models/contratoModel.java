package cl.soge.api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Sirve para generar los getters y setters
@AllArgsConstructor // Sirve para generar un constructor con todos los argumentos
@NoArgsConstructor // Sirve para generar un constructor vacío
@Table(name = "Contrato")
/**
 * Clase que representa la tabla Contrato de la base de datos
 */
public class contratoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer idContrato;
    private Boolean calificarContrato;
    private String urlContrato;
}
