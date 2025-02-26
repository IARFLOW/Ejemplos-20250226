
package com.example.demo.controllers;

import com.example.demo.models.Alumno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumnos")

public class AlumnoController {
    
    private List<Alumno> alumnos = new ArrayList<>();

    public AlumnoController() {
        alumnos.add(new Alumno(1, "Juan", 20));
        alumnos.add(new Alumno(2, "María", 22));
        alumnos.add(new Alumno(3, "Juan2", 23));
        alumnos.add(new Alumno(4, "Juan3", 24));
        alumnos.add(new Alumno(5, "Juan4", 25));
        alumnos.add(new Alumno(6, "Juan5", 26));
    }
    
    //hace GET en el protocolo HTTP: devuelve el array.
    @GetMapping
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    
    //hace GET con un id fijo
    @GetMapping("/{id}")
    public Alumno getAlumno(@PathVariable int id) {
        return alumnos.stream()
                .filter(alumno -> alumno.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
