import { Component, OnInit } from '@angular/core';
import { ProntuarioService } from '../../services/prontuario.service';
import { Prontuario } from '../../models/prontuario.model';

@Component({
  selector: 'app-prontuario-list',
  templateUrl: './prontuario-list.component.html',
  styleUrls: ['./prontuario-list.component.scss']
})
export class ProntuarioListComponent implements OnInit {
  prontuarios: Prontuario[] = [];
  loading = true;
  error = '';

  constructor(private prontuarioService: ProntuarioService) { }

  ngOnInit(): void {
    this.loadProntuarios();
  }

  loadProntuarios(): void {
    this.loading = true;
    this.prontuarioService.getAllProntuarios().subscribe({
      next: (data) => {
        this.prontuarios = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar prontuários. Por favor, tente novamente.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  deleteProntuario(id: number): void {
    if (confirm('Tem certeza que deseja excluir este prontuário?')) {
      this.prontuarioService.deleteProntuario(id).subscribe({
        next: () => {
          this.prontuarios = this.prontuarios.filter(p => p.id !== id);
        },
        error: (err) => {
          this.error = 'Erro ao excluir prontuário. Por favor, tente novamente.';
          console.error(err);
        }
      });
    }
  }
} 