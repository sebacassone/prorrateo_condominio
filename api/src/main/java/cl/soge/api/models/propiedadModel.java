package cl.soge.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "propiedad")
public class propiedadModel {

    @Id
    @Column(name = "id_propiedad", nullable = false, unique = true)
    private Integer numero_departamento;
    private Integer tamaño_inmueble;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private usuarioModel usuario;

    @OneToOne
    @JoinColumn(name = "id_contrato")
    private contratoModel contrato;

    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private edificioModel edificio;

    @OneToOne
    @JoinColumn(name = "id_prorrateo")
    private prorrateoModel prorrateo;
}
