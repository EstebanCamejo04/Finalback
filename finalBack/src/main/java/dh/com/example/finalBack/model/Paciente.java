package dh.com.example.finalBack.model;

import org.springframework.core.style.ToStringCreator;

import java.sql.Date;
import java.time.LocalDate;

public class Paciente {
    private Integer id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String dni;
    private LocalDate fechaAltaPrivat;

    public Paciente() {
    }

    public Paciente(Integer id, String nombre, String apellido, Domicilio domicilio, String dni, LocalDate fechaAltaPrivat) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAltaPrivat = fechaAltaPrivat;
    }

    public Paciente(String nombre, String apellido, Domicilio domicilio, String dni, LocalDate fechaAltaPrivat) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAltaPrivat = fechaAltaPrivat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAltaPrivat() {
        return fechaAltaPrivat;
    }

    public void setFechaAltaPrivat(LocalDate fechaAltaPrivat) {
        this.fechaAltaPrivat = fechaAltaPrivat;
    }


    @Override
    public String toString() {
        return "id" + id + "  nombre" + nombre + "  " +apellido + "  domicilio"+ domicilio + "  dni"+  dni + "  fechaAltaPrivat" +fechaAltaPrivat;
    }
}

