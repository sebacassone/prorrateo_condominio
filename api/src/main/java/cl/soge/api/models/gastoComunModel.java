package cl.soge.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gasto_comun")
public class gastoComunModel {
    @Id
    @Column(name = "id_gasto", nullable = false, unique = true)
    private Integer idGasto;
    private String descripcion_gasto;
    private Integer monto_gasto;
    private Date fecha_emision;
    private Date fecha_registro;

    @ManyToOne
    @JoinColumn(name = "id_eficio")
    private edificioModel edificio;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuarioModel usuario;

}
