export type StatusAtividade = 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA';

export interface Atividade {
  id: number;
  nome: string;
  voluntarios: number;
  cargaHorariaDiaria: string;
  periodo: string;
  criadoEm: string;
  statusAtvidade: StatusAtividade;
  descricao: string;
}
