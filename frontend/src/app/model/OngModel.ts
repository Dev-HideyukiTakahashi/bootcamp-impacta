export interface CarregarDadosOng {
  nomeEntidade: string;
  cnpj: string;
  email: string;
  telefone: string;
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

export interface PerfilOng {
  nomeEntidade: string;
  cnpj: string;
  estado: string;
  cidade: string;
  rua: string;
  numero: string;
}

export interface AtualizarOng {
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



