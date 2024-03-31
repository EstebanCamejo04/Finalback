package dh.com.example.finalBack.repository;

import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findPacienteByDni(String dni);
}
