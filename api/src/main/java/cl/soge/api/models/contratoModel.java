package cl.soge.api.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Contrato")
public class contratoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato", nullable = false)
    private Integer idContrato;
    @Column(name = "calificar_contrato")
    private Boolean calificarContrato;
    @Column(name = "url_contrato")
    private String urlContrato;
}
