package cl.soge.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gasto_comun_seq", sequenceName = "gasto_comun_seq", allocationSize = 1)
@Table(name = "gasto_comun")
public class gastoComunModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gasto_comun_seq") // Esta línea ha sido modificada
    @Column(name = "id_gasto", nullable = false, unique = true)
    private Integer idGasto;

    private String descripcionGasto;
    private Integer montoGasto;
    private Date fechaEmision;
    private Date fechaRegistro;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private edificioModel edificio;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuarioModel usuario;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "gastoCategoria",
            joinColumns = {@JoinColumn(name="id_gasto")},
            inverseJoinColumns = {@JoinColumn(name="id_categoria")}
    )
    private List<categoriaModel> categorias;

}