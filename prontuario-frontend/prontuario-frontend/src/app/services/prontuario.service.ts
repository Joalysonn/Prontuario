import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prontuario } from '../models/prontuario.model';

@Injectable({
  providedIn: 'root'
})
export class ProntuarioService {
  private apiUrl = 'http://localhost:8080/api/prontuarios';

  constructor(private http: HttpClient) { }

  getAllProntuarios(): Observable<Prontuario[]> {
    return this.http.get<Prontuario[]>(this.apiUrl);
  }

  getProntuarioById(id: number): Observable<Prontuario> {
    return this.http.get<Prontuario>(`${this.apiUrl}/${id}`);
  }

  getProntuariosByPacienteId(pacienteId: number): Observable<Prontuario[]> {
    return this.http.get<Prontuario[]>(`${this.apiUrl}/paciente/${pacienteId}`);
  }

  createProntuario(prontuario: Prontuario): Observable<Prontuario> {
    return this.http.post<Prontuario>(this.apiUrl, prontuario);
  }

  updateProntuario(id: number, prontuario: Prontuario): Observable<Prontuario> {
    return this.http.put<Prontuario>(`${this.apiUrl}/${id}`, prontuario);
  }

  deleteProntuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/${id}/pdf`, {
      responseType: 'blob'
    });
  }
} 