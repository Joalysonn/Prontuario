package com.smart.prontuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart.prontuario.model.Prontuario;
import com.smart.prontuario.repository.ProntuarioRepository;

@Service
public class ProntuarioService {
    private static final Logger logger = LoggerFactory.getLogger(ProntuarioService.class);
    
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    
    public List<Prontuario> findAll() {
        List<Prontuario> prontuarios = prontuarioRepository.findAllWithPaciente();
        logger.info("Encontrados {} prontuários", prontuarios.size());
        prontuarios.forEach(p -> logger.info("Prontuário ID: {}, Paciente: {}", p.getId(), p.getPaciente() != null ? p.getPaciente().getNome() : "null"));
        return prontuarios;
    }
    
    public Prontuario findById(Long id) {
        Prontuario prontuario = prontuarioRepository.findByIdWithPaciente(id);
        logger.info("Prontuário encontrado: ID: {}, Paciente: {}", id, prontuario != null && prontuario.getPaciente() != null ? prontuario.getPaciente().getNome() : "null");
        return prontuario;
    }
    
    public List<Prontuario> findByPacienteId(Long pacienteId) {
        return prontuarioRepository.findByPacienteId(pacienteId);
    }
    
    public Prontuario save(Prontuario prontuario) {
        logger.info("Salvando prontuário: ID: {}, Paciente: {}", prontuario.getId(), prontuario.getPaciente() != null ? prontuario.getPaciente().getNome() : "null");
        return prontuarioRepository.save(prontuario);
    }
    
    public void delete(Long id) {
        prontuarioRepository.deleteById(id);
    }
}