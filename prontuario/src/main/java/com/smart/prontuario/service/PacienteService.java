package com.smart.prontuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.prontuario.model.Paciente;
import com.smart.prontuario.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
    
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }
    
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    
    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }
}