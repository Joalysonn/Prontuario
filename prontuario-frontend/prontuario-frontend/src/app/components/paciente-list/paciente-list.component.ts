import { Component, OnInit } from '@angular/core';
import { PacienteService } from '../../services/paciente.service';
import { Paciente } from '../../models/paciente.model';

@Component({
  selector: 'app-paciente-list',
  templateUrl: './paciente-list.component.html',
  styleUrls: ['./paciente-list.component.scss']
})
export class PacienteListComponent implements OnInit {
  pacientes: Paciente[] = [];
  loading = true;
  error = '';

  constructor(private pacienteService: PacienteService) { }

  ngOnInit(): void {
    this.loadPacientes();
  }

  loadPacientes(): void {
    this.loading = true;
    this.pacienteService.getAllPacientes().subscribe({
      next: (data) => {
        this.pacientes = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar pacientes. Por favor, tente novamente.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  deletePaciente(id: number): void {
    if (confirm('Tem certeza que deseja excluir este paciente?')) {
      this.pacienteService.deletePaciente(id).subscribe({
        next: () => {
          this.pacientes = this.pacientes.filter(p => p.id !== id);
        },
        error: (err) => {
          this.error = 'Erro ao excluir paciente. Por favor, tente novamente.';
          console.error(err);
        }
      });
    }
  }
} 