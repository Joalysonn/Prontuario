import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProntuarioService } from '../../services/prontuario.service';
import { PacienteService } from '../../services/paciente.service';
import { Prontuario } from '../../models/prontuario.model';
import { Paciente } from '../../models/paciente.model';

@Component({
  selector: 'app-prontuario-form',
  templateUrl: './prontuario-form.component.html',
  styleUrls: ['./prontuario-form.component.scss']
})
export class ProntuarioFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  loading = false;
  error = '';
  pacientes: Paciente[] = [];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private prontuarioService: ProntuarioService,
    private pacienteService: PacienteService
  ) {
    this.form = this.fb.group({
      paciente: ['', Validators.required],
      dataConsulta: ['', Validators.required],
      sintomas: ['', Validators.required],
      diagnostico: ['', Validators.required],
      prescricao: [''],
      observacoes: [''],
      nomeMedico: ['', Validators.required],
      crmMedico: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadPacientes();
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.loadProntuario(id);
    }
  }

  loadPacientes(): void {
    this.pacienteService.getAllPacientes().subscribe({
      next: (data) => {
        this.pacientes = data;
      },
      error: (err) => {
        this.error = 'Erro ao carregar pacientes. Por favor, tente novamente.';
        console.error(err);
      }
    });
  }

  loadProntuario(id: number): void {
    this.loading = true;
    this.prontuarioService.getProntuarioById(id).subscribe({
      next: (prontuario) => {
        this.form.patchValue({
          ...prontuario,
          paciente: prontuario.paciente.id
        });
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar prontuário. Por favor, tente novamente.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.loading = true;
      const prontuario: Prontuario = {
        ...this.form.value,
        paciente: this.pacientes.find(p => p.id === this.form.value.paciente)!
      };

      const request = this.isEdit
        ? this.prontuarioService.updateProntuario(this.route.snapshot.params['id'], prontuario)
        : this.prontuarioService.createProntuario(prontuario);

      request.subscribe({
        next: () => {
          this.router.navigate(['/prontuarios']);
        },
        error: (err) => {
          this.error = 'Erro ao salvar prontuário. Por favor, tente novamente.';
          this.loading = false;
          console.error(err);
        }
      });
    }
  }
} 