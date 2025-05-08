import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PacienteListComponent } from './components/paciente-list/paciente-list.component';
import { PacienteFormComponent } from './components/paciente-form/paciente-form.component';
import { ProntuarioListComponent } from './components/prontuario-list/prontuario-list.component';
import { ProntuarioFormComponent } from './components/prontuario-form/prontuario-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PacienteListComponent,
    PacienteFormComponent,
    ProntuarioListComponent,
    ProntuarioFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { } 