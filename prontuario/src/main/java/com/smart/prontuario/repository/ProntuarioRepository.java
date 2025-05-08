package com.smart.prontuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.prontuario.model.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    List<Prontuario> findByPacienteId(Long pacienteId);
}