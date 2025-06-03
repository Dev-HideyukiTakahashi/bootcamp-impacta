// src/app/model/atividade.model.ts (ou src/app/features/...)
export type StatusAtividade = 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA';
export type Periodo = 'MANHA' | 'TARDE' | 'NOITE';


export interface Atividade {
  id: number;
  nome: string;
  periodo: Periodo;
  cargaHorariaDiaria: number;
  enderecoCompleto: string;
  possuiCertificacao: boolean;
  descricao: string;
  statusAtividade: StatusAtividade;
  dataAtividade: Date;
  idTag: number;
  voluntariosCount: number;
}
