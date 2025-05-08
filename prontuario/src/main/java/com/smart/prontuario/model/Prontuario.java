package com.smart.prontuario.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "prontuarios")
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    private LocalDateTime dataConsulta;
    private String sintomas;
    private String diagnostico;
    private String prescricao;
    private String observacoes;
    
    private String nomeMedico;
    private String crmMedico;
}