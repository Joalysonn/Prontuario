import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PacienteListComponent } from './components/paciente-list/paciente-list.component';
import { PacienteFormComponent } from './components/paciente-form/paciente-form.component';
import { ProntuarioListComponent } from './components/prontuario-list/prontuario-list.component';
import { ProntuarioFormComponent } from './components/prontuario-form/prontuario-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/pacientes', pathMatch: 'full' },
  { path: 'pacientes', component: PacienteListComponent },
  { path: 'pacientes/novo', component: PacienteFormComponent },
  { path: 'pacientes/:id', component: PacienteFormComponent },
  { path: 'prontuarios', component: ProntuarioListComponent },
  { path: 'prontuarios/novo', component: ProntuarioFormComponent },
  { path: 'prontuarios/:id', component: ProntuarioFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 