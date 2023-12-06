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
    @Column(nullable = false, unique = true)
    private Integer numeroDepartamento;

    private Integer tamañoInmueble;

    @JsonIgnore
    @ManyToMany(mappedBy = "propiedades")
    private List<usuarioModel> usuarios;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_contrato")
    private contratoModel contrato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private edificioModel edificio;
}
