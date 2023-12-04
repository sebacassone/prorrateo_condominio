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
@Table(name = "gasto_comun")
public class gastoComunModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta línea ha sido modificada
    @Column(name = "id_gasto", nullable = false, unique = true)
    private Integer idGasto;

    private String descripcion_gasto;
    private Integer monto_gasto;
    private Date fecha_emision;
    private Date fecha_registro;

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