import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // necessário para [(ngModel)] no <select>
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';


interface Voluntario {
  id: number;
  nome: string;
  avatarUrl?: string;
  cidade: string;
  habilidade: string;
  atividade?: string;
  projeto?: string;
  dataEncerramento?: Date;
  status: 'PENDENTE' | 'REALIZADO' | 'APROVADO' | 'REJEITADO';
}

@Component({
  selector: 'app-gestao-voluntarios',
  templateUrl: './gestao-voluntarios.component.html',
  styleUrls: ['./gestao-voluntarios.component.scss'],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HeaderComponent,
    FooterComponent,
  ],
})
export class GestaoVoluntariosComponent implements OnInit {
  // dados “mock” para testar o front
  voluntarios: Voluntario[] = [
    {
      id: 1,
      nome: 'Carlos Santos',
      cidade: 'Guarulhos/SP',
      habilidade: 'Construção/Manutenção',
      atividade: 'Ajuda com reforma na quadra esportiva da ONG',
      status: 'PENDENTE'
    },
    {
      id: 2,
      nome: 'Beatriz Teles',
      cidade: 'Guarulhos/SP',
      habilidade: 'Tradução',
      projeto: 'Tradução de livros infantis em inglês',
      dataEncerramento: new Date('2025-03-12'),
      status: 'REALIZADO'
    },
    {
      id: 3,
      nome: 'André Junior',
      cidade: 'Guarulhos/SP',
      habilidade: 'Meio Ambiente e Sustentabilidade',
      atividade: 'Ajudar no plantio de árvores',
      status: 'APROVADO'
    },
    {
      id: 4,
      nome: 'Renato Penha',
      cidade: 'Sorocaba/SP',
      habilidade: 'Alimentação Comunitária',
      atividade: 'Preparo de marmitas para desabrigados',
      status: 'PENDENTE'
    },
    {
      id: 5,
      nome: 'Ana Silva',
      cidade: 'Rio de Janeiro/RJ',
      habilidade: 'Educação e Mentoria',
      atividade: 'Reforço de aula de matemática',
      status: 'REJEITADO'
    },
    {
      id: 6,
      nome: 'Maria Oliveira',
      cidade: 'Campinas/SP',
      habilidade: 'Saúde e Bem-Estar',
      atividade: 'Apoio a crianças vítimas de violência doméstica',
      status: 'PENDENTE'
    }
  ];

  // paginação
  currentPage = 1;
  pageSize = 5;
  get totalPages(): number {
    return Math.ceil(this.voluntarios.length / this.pageSize);
  }
  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }
  get paginatedVoluntarios(): Voluntario[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.voluntarios.slice(start, start + this.pageSize);
  }

  constructor() {}

  ngOnInit(): void {}

  // ações de teste
  aceitar(v: Voluntario) {
    v.status = 'APROVADO';
  }
  rejeitar(v: Voluntario) {
    v.status = 'REJEITADO';
  }
  avaliar(v: Voluntario) {
    // para testar, só muda o status
    v.status = 'APROVADO';
  }
  encerrar(v: Voluntario) {
    v.status = 'REALIZADO';
    v.dataEncerramento = new Date();
  }

  // navegação de páginas
  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }
  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }
  goToPage(n: number) {
    this.currentPage = n;
  }
}
