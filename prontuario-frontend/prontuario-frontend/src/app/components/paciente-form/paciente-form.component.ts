import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PacienteService } from '../../services/paciente.service';
import { Paciente } from '../../models/paciente.model';

@Component({
  selector: 'app-paciente-form',
  templateUrl: './paciente-form.component.html',
  styleUrls: ['./paciente-form.component.scss']
})
export class PacienteFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  loading = false;
  error = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private pacienteService: PacienteService
  ) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      dataNascimento: ['', Validators.required],
      telefone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      endereco: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.loadPaciente(id);
    }
  }

  loadPaciente(id: number): void {
    this.loading = true;
    this.pacienteService.getPacienteById(id).subscribe({
      next: (paciente) => {
        this.form.patchValue(paciente);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar paciente. Por favor, tente novamente.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.loading = true;
      const paciente: Paciente = this.form.value;

      const request = this.isEdit
        ? this.pacienteService.updatePaciente(this.route.snapshot.params['id'], paciente)
        : this.pacienteService.createPaciente(paciente);

      request.subscribe({
        next: () => {
          this.router.navigate(['/pacientes']);
        },
        error: (err) => {
          this.error = 'Erro ao salvar paciente. Por favor, tente novamente.';
          this.loading = false;
          console.error(err);
        }
      });
    }
  }
} 