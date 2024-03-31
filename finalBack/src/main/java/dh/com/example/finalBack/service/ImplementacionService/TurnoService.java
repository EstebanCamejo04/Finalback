package dh.com.example.finalBack.service.ImplementacionService;

import dh.com.example.finalBack.entity.Turno;
import dh.com.example.finalBack.repository.ITurnoRepository;
import dh.com.example.finalBack.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }


    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            return turnoOptional.get();
        } else {
            return null;
        }
    }

        @Override
        public void actualizar (Turno turno){
            turnoRepository.save(turno);
        }

        @Override
        public void eliminar(Long id) {

        }

}
