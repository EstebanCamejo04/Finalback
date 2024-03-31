package dh.com.example.finalBack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaAltaPrivat;
    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<Turno> turnoSet = new HashSet<>();

}
