package dh.com.example.finalBack.service.ImplementacionService;

import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.dao.implementacionDao.DomicilioDaoH2;
import dh.com.example.finalBack.model.Domicilio;
import dh.com.example.finalBack.service.IDomicilioService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DomicilioService implements IDomicilioService {

    private IDao<Domicilio> iDao;

    public DomicilioService() {
        iDao = new DomicilioDaoH2();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return iDao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}
