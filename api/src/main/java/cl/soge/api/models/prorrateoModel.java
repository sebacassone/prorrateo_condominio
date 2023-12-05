package cl.soge.api.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prorrateo")
public class prorrateoModel {

    @Id
    @Column(name = "id_prorrateo", nullable = false, unique = true)
    private Integer idProrrateo;

    private String criterio_prorrateo;
    private Date fecha_prorrateo;
    private Date fecha_vencimiento;
}
