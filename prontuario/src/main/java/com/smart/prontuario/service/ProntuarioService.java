package com.smart.prontuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.prontuario.model.Prontuario;
import com.smart.prontuario.repository.ProntuarioRepository;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    
    public List<Prontuario> findAll() {
        return prontuarioRepository.findAll();
    }
    
    public Prontuario findById(Long id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
    }
    
    public List<Prontuario> findByPacienteId(Long pacienteId) {
        return prontuarioRepository.findByPacienteId(pacienteId);
    }
    
    public Prontuario save(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }
    
    public void delete(Long id) {
        prontuarioRepository.deleteById(id);
    }
}