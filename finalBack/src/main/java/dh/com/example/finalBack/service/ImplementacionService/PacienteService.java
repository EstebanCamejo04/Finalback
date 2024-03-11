package dh.com.example.finalBack.service.ImplementacionService;

import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.dao.implementacionDao.PacienteDaoH2;
import dh.com.example.finalBack.model.Paciente;
import dh.com.example.finalBack.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> iDao;

    public PacienteService() {
        iDao = new PacienteDaoH2();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}

