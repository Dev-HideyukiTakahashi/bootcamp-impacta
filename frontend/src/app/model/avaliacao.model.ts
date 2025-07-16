export interface AvaliacaoRequest {
  historicoAtividadeId: number;
  feedback?: string;
  estrelas: number;
  cargaHoraria: string;
}

export interface AvaliacaoResponse {
  id: number;
  feedback: string;
  estrelas: number;
  voluntarioNome: string;
  atividadeNome: string;
}
export interface AvaliacaoDTO {
  voluntarioId: number;
  participou: boolean;
  horas: number;
  comentario: string;
  nota: number;
}
