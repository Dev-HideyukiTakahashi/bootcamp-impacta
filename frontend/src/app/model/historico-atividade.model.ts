export interface HistoricoAtividade {
  id: number;
  descricao: string;
  cargaHorariaDiaria: string;
  periodo: PeriodoAtividade;
  dataInscricao: string;
  estrelas: number;
  certificado: boolean;
  ong: string;
  feedback?: string;
  encerradoEm: string; 
}

export type PeriodoAtividade = 'MANHA' | 'TARDE' | 'NOITE';
