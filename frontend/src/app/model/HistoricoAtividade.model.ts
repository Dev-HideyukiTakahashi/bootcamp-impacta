export type statusCandidatura= 'APROVADO' | 'REJEITADO' | 'PENDENTE';

export interface HistoricoAtividade {
  atividade_id: number;
  certificado: boolean;
  statusCandidatura: statusCandidatura; // 'APROVADO', 'REJEITADO', 'PENDENTE'
  idAvaliacao: number ;
  idVoluntario: number; // id do voluntario
  id: number; // sequence da tbl, precisa?
}
export interface HistoricoAtividadeDetalhado {
  nomeVoluntario: string;
  cidade: string;
  tags: string[];
  statusCandidatura: statusCandidatura;
}
