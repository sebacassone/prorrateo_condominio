package cl.soge.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "idEdficio")
    private edificioModel edificio;
}
