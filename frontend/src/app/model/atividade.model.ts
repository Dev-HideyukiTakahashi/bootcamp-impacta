// src/app/model/atividade.model.ts (ou src/app/features/...)
export type StatusAtividade = 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA';
export type Periodo = 'MANHA' | 'TARDE' | 'NOITE';

export interface Atividade {
  id: number;
  nome: string;
  periodo: Periodo;
  cargaHorariaDiaria: String;
  enderecoCompleto: string;
  possuiCertificacao: boolean;
  descricao: string;
  statusAtividade: StatusAtividade;
  dataAtividade: Date;
  idTag: number;
  voluntariosCount?: number;
}

export interface IAtividade {
  id: number;
  idTag: number | null;
  titulo?: string | null;
  nome: string;
  periodo: Periodo;
  cargaHorariaDiaria: string;
  enderecoCompleto: string;
  possuiCertificacao: boolean;
  descricao: string;
  statusAtividade: StatusAtividade;
  dataAtividade: Date | null;
  idOng: number;
  statusCandidatura: string | null;
}

// atualizarAtividade , campos para alterar
export interface AtualizarAtividade {
  nome: string;
  periodo: Periodo;
  cargaHorariaDiaria: String;
  enderecoCompleto: string;
  possuiCertificacao: boolean;
  descricao: string;
  statusAtividade: StatusAtividade;
  dataAtividade: Date;
}

// carregarDadosAtividade, campos para carregar dados
export interface CarregarDadosAtividade {
  nome: string;
  periodo: Periodo;
  cargaHorariaDiaria: String;
  enderecoCompleto: string;
  possuiCertificacao: boolean;
  descricao: string;
  statusAtividade: StatusAtividade;
  dataAtividade: Date;
}
