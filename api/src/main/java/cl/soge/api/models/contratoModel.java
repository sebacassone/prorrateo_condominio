package cl.soge.api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Contrato")
public class contratoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato", nullable = false, unique = true)
    private Integer idContrato;
    @Column(name = "calificar_contrato")
    private Boolean calificarContrato;
    @Column(name = "url_contrato")
    private String urlContrato;
}
