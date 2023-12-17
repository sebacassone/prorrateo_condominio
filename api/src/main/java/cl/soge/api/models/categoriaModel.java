package cl.soge.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // Sirve para generar los getters y setters
@AllArgsConstructor // Sirve para generar un constructor con todos los argumentos
@NoArgsConstructor // Sirve para generar un constructor vacío
@Table(name = "categoria")

/**
 * Clase que representa la tabla categoria de la base de datos
 */
public class categoriaModel {
    @Id
    @Column(name = "id_categoria",  nullable = false, unique = true)
    private Integer idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    // Relación muchos a muchos con la tabla gasto_comun
    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private List<gastoComunModel> gastosComunes;

}
