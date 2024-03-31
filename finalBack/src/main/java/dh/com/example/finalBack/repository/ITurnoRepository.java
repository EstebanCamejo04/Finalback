package dh.com.example.finalBack.repository;

import dh.com.example.finalBack.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
