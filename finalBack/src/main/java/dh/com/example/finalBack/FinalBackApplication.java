package dh.com.example.finalBack;

import dh.com.example.finalBack.dao.BD;
import dh.com.example.finalBack.model.Domicilio;
import dh.com.example.finalBack.model.Odontologo;
import dh.com.example.finalBack.model.Paciente;
import dh.com.example.finalBack.service.IDomicilioService;
import dh.com.example.finalBack.service.IOdontologoService;
import dh.com.example.finalBack.service.IPacienteService;
import dh.com.example.finalBack.service.ImplementacionService.DomicilioService;
import dh.com.example.finalBack.service.ImplementacionService.OdontologoService;
import dh.com.example.finalBack.service.ImplementacionService.PacienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class FinalBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalBackApplication.class, args);
		BD.crearTablas();

		Odontologo odontologo = new Odontologo(1,"Esteban","Camejo", "LL45");
		IOdontologoService odontologoService = new OdontologoService();
		odontologoService.guardar(odontologo);
		System.out.println(odontologo);
		odontologoService.listarTodos();

		Domicilio domicilio = new Domicilio(1,"lele",123,"pollo","atajuma");
		IDomicilioService domicilioService = new DomicilioService();
		domicilioService.guardar(domicilio);
		System.out.println(domicilio);
		domicilioService.listarTodos();

		Paciente paciente = new Paciente(1,"jamon","queso",domicilio,"1234", new Date(new java.util.Date().getTime()).toLocalDate());
		IPacienteService pacienteService = new PacienteService();
		pacienteService.guardar(paciente);
		System.out.println(paciente);
		pacienteService.listarTodos();
	}
}