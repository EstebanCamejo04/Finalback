package dh.com.example.finalBack.service.ImplementacionService;

import dh.com.example.finalBack.controller.OdontologoController;
import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.exception.OdontologoMatriculaException;
import dh.com.example.finalBack.exception.ResourceNotFoundException;
import dh.com.example.finalBack.repository.IOdontologoRepository;
import dh.com.example.finalBack.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findOdontologoByMatricula(odontologo.getMatricula());
        if (!odontologoOptional.isPresent()){
            return odontologoRepository.save(odontologo);
        } else {
            LOGGER.error("Ya existe odontologo con matricula " + odontologo.getMatricula());
            throw new OdontologoMatriculaException("Ya existe odontologo con matricula " + odontologo.getMatricula());
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if (odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        } else {
            LOGGER.error("No se encontró el odontólogo con id " + id);
            throw new ResourceNotFoundException("No se encontró el odontólogo con id " + id);
        }

    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(odontologo.getId());
        if (odontologoOptional.isPresent()) {
            return odontologoRepository.save(odontologo);
        } else {
            LOGGER.error("No se encontró el odontólogo con id " + odontologo.getId());
            throw new ResourceNotFoundException("No se encontró el odontólogo con id " + odontologo.getId());
        }
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}

