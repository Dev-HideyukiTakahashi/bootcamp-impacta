import { Tag } from './tag.model';

export interface CarregarDadosVoluntario {
  nomeCompleto: string;
  email: string;
  cpf: string;
  telefone: string;
  dataNascimento: string;
  pais: string;
  cep: string;
  estado: string;
  cidade: string;
  bairro: string;
  rua: string;
  numero: string;
  senha?: string;
  confirmarSenha?: string;
}

export interface PerfilVoluntario {
  firstName: string;
  fullName: string;
  cidade: string;
  estado: string;
  tags: Tag[];
  participacoes: string;
}

export interface AtualizarVoluntario {
  telefone: string;
  endereco: {
    cep: string;
    estado: string;
    cidade: string;
    bairro: string;
    rua: string;
    numero: string;
  };
  alterarSenha: string;
  senha: string | null;
}
