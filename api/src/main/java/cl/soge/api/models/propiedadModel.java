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
@Table(name = "propiedad")
/**
 * Clase que representa la tabla Propiedad de la base de datos
 */
public class propiedadModel {

    @Id
    @Column(nullable = false, unique = true)
    private Integer numeroDepartamento;

    private Integer tamañoInmueble;

    // Relación muchos a muchos con la tabla usuario
    @JsonIgnore
    @ManyToMany(mappedBy = "propiedades")
    private List<usuarioModel> usuarios;

    // Relación uno a uno con la tabla contrato
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_contrato")
    private contratoModel contrato;

    // Relación muchos a uno con la tabla edificio
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private edificioModel edificio;
}
