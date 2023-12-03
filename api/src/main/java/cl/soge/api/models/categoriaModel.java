package cl.soge.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
