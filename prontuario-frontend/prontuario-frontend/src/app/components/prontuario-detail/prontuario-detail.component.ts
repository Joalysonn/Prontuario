import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProntuarioService } from '../../services/prontuario.service';
import { Prontuario } from '../../models/prontuario.model';

@Component({
  selector: 'app-prontuario-detail',
  templateUrl: './prontuario-detail.component.html',
  styleUrls: ['./prontuario-detail.component.scss']
})
export class ProntuarioDetailComponent implements OnInit {
  prontuario!: Prontuario;
  loading = false;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private prontuarioService: ProntuarioService
  ) {}

  ngOnInit(): void {
    this.loadProntuario();
  }

  loadProntuario(): void {
    const id = Number(this.route.snapshot.params['id']);
    if (!id) {
      this.error = 'ID do prontuário não encontrado';
      return;
    }

    this.loading = true;
    this.prontuarioService.getProntuarioById(id).subscribe({
      next: (data) => {
        this.prontuario = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar prontuário. Por favor, tente novamente.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  exportarPdf(): void {
    if (!this.prontuario?.id) {
      this.error = 'Prontuário não encontrado';
      return;
    }

    this.loading = true;
    this.prontuarioService.downloadPdf(this.prontuario.id).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = `prontuario_${this.prontuario.paciente.nome}.pdf`;
        link.click();
        window.URL.revokeObjectURL(url);
        this.loading = false;
      },
      error: (err) => {
        console.error('Erro ao baixar PDF:', err);
        this.loading = false;
      }
    });
  }
} 