import { Paciente } from './paciente.model';

export interface Prontuario {
    id?: number;
    paciente: Paciente;
    dataConsulta: string;
    sintomas: string;
    diagnostico: string;
    prescricao: string;
    observacoes: string;
    nomeMedico: string;
    crmMedico: string;
} 