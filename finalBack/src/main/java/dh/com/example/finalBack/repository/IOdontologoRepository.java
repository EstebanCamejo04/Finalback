package dh.com.example.finalBack.repository;

import dh.com.example.finalBack.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    public Optional <Odontologo> findOdontologoByMatricula (String matricula);

}
