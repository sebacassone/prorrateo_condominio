package cl.soge.api.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Edificio")
public class edificioModel {
    private Integer idEdificio;
    private String nombreEdificio;
    private String direccionEdificio;
}
