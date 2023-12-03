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
@Table(name = "usuario")
public class usuarioModel {
    @Id
    @Column(nullable = false, unique = true)
    private String rut;
    private String nombre;
    private String apellido;
    private Integer tipoUsuario; // 0, 1, 2, 3
    private String correo;
    private String contraseña;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEdificio")
    private edificioModel edificio;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "usuarioPropiedad",
            joinColumns = {@JoinColumn(name="rut")},
            inverseJoinColumns = {@JoinColumn(name="numero_departamento")}
    )
    List<propiedadModel> propiedades;

    // La relación de muchos es a muchos de notificación no se escribe todavía por que no es necesario
    // para los requerimientos funcionales actuales.
}
