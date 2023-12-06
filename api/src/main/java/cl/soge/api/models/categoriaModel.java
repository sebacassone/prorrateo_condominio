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
@Table(name = "categoria")

public class categoriaModel {
    @Id
    @Column(name = "id_categoria",  nullable = false, unique = true)
    private Integer idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private List<gastoComunModel> gastosComunes;

}
