package dh.com.example.finalBack.service.ImplementacionService;


import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.dao.implementacionDao.OdontologoDaoH2;
import dh.com.example.finalBack.model.Odontologo;
import dh.com.example.finalBack.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;

    public OdontologoService() {
        iDao = new OdontologoDaoH2();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}

