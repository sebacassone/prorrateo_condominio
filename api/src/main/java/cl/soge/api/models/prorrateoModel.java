package cl.soge.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data // Sirve para generar los getters y setters
@AllArgsConstructor // Sirve para generar un constructor con todos los argumentos
@NoArgsConstructor // Sirve para generar un constructor vacío
@SequenceGenerator(name = "prorrateo_seq", sequenceName = "prorrateo_seq", allocationSize = 1)
@Table(name = "prorrateo")
public class prorrateoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prorrateo_seq")
    @Column(name = "id_prorrateo", nullable = false, unique = true)
    private Integer idProrrateo;

    private String criterioProrrateo;
    private Date mesAñoProrrateo;
    private Date fechaVencimiento;
    private Integer montoProrrateo;

    // Relación muchos a uno con la tabla propiedad
    @ManyToOne
    @JoinColumn(name = "numero_departamento")
    private propiedadModel propiedad;
}
