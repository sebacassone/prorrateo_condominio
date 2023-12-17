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
@Table(name = "usuario")
public class usuarioModel {
    @Id
    @Column(nullable = false, unique = true)
    private String rut;
    private String nombre;
    private String apellido;
    private Integer tipoUsuario; // 0 = Admin, 1 = Residente, y 2 = Propietario
    private String correo;
    private String password;

    // Relación muchos a uno con la tabla edificio
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEdificio")
    private edificioModel edificio;

    // Relación muchos a muchos con la tabla propiedad
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
