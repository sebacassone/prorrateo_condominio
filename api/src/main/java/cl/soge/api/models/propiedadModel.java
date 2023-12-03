package cl.soge.api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuarioModel usuario;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_contrato")
    private contratoModel contrato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private edificioModel edificio;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_prorrateo")
    private prorrateoModel prorrateo;
}
