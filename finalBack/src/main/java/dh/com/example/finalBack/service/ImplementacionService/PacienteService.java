package dh.com.example.finalBack.service.ImplementacionService;

import dh.com.example.finalBack.entity.Paciente;
import dh.com.example.finalBack.exception.PacienteDniException;
import dh.com.example.finalBack.exception.ResourceNotFoundException;
import dh.com.example.finalBack.repository.IPacienteRepository;
import dh.com.example.finalBack.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        Optional<Paciente> pacienteOptional = pacienteRepository.findPacienteByDni(paciente.getDni());
        if (!pacienteOptional.isPresent()){
            return pacienteRepository.save(paciente);
        } else {
            LOGGER.error("Ya existe paciente con Dni " + paciente.getDni());
            throw new PacienteDniException("Ya existe paciente con Dni " + paciente.getDni());

        }
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        } else {
            LOGGER.info("No existe paciente con id " + id );
            throw new ResourceNotFoundException("No existe paciente con id " + id );
        }
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(paciente.getId());
        if (pacienteOptional.isPresent()) {
            return pacienteRepository.save(paciente);
        } else {
            LOGGER.info("No existe paciente con id " + paciente.getId() );
            throw new ResourceNotFoundException("No existe paciente con id " + paciente.getId() );
        }

    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

}

