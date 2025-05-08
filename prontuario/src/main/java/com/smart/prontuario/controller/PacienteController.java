package com.smart.prontuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.prontuario.model.Paciente;
import com.smart.prontuario.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }
    
    @GetMapping("/{id}")
    public Paciente getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }
    
    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }
    
    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        return pacienteService.save(paciente);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
