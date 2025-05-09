package com.smart.prontuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.smart.prontuario.model.Prontuario;
import com.smart.prontuario.service.ProntuarioService;
import com.smart.prontuario.service.ProntuarioPdfService;

@RestController
@RequestMapping("/api/prontuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ProntuarioController {
    @Autowired
    private ProntuarioService prontuarioService;
    
    @Autowired
    private ProntuarioPdfService prontuarioPdfService;
    
    @GetMapping
    public List<Prontuario> getAllProntuarios() {
        return prontuarioService.findAll();
    }
    
    @GetMapping("/{id}")
    public Prontuario getProntuarioById(@PathVariable Long id) {
        return prontuarioService.findById(id);
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public List<Prontuario> getProntuariosByPacienteId(@PathVariable Long pacienteId) {
        return prontuarioService.findByPacienteId(pacienteId);
    }
    
    @PostMapping
    public Prontuario createProntuario(@RequestBody Prontuario prontuario) {
        return prontuarioService.save(prontuario);
    }
    
    @PutMapping("/{id}")
    public Prontuario updateProntuario(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        prontuario.setId(id);
        return prontuarioService.save(prontuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProntuario(@PathVariable Long id) {
        prontuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id) {
        try {
            Prontuario prontuario = prontuarioService.findById(id);
            byte[] pdfBytes = prontuarioPdfService.generatePdf(prontuario);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "prontuario_" + prontuario.getPaciente().getNome() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
