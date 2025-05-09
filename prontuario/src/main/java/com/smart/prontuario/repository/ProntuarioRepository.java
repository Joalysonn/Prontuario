package com.smart.prontuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smart.prontuario.model.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    
    @Query("SELECT p FROM Prontuario p LEFT JOIN FETCH p.paciente WHERE p.id = :id")
    Prontuario findByIdWithPaciente(Long id);
    
    @Query("SELECT p FROM Prontuario p LEFT JOIN FETCH p.paciente")
    List<Prontuario> findAllWithPaciente();
    
    @Query("SELECT p FROM Prontuario p LEFT JOIN FETCH p.paciente WHERE p.paciente.id = :pacienteId")
    List<Prontuario> findByPacienteId(Long pacienteId);
}