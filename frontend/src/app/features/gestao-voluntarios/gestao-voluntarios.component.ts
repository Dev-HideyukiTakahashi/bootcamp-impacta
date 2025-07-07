import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
// importar meu modal avaliar
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import {
  GestaoVoluntarioService,
  ListaInscritos,
  StatusCandidatura,
} from '../../service/gestaoVoluntario.service';
import { ModalAvaliarVoluntarioComponent } from './modal-avaliar-voluntario/modal-avaliar-voluntario.component';

// Interface que representa o DTO de resposta do voluntário
export interface VoluntarioHistoricoResponseDTO {
  id: number;
  nomeCompleto: string;
  statusCandidatura: 'PENDENTE' | 'APROVADO' | 'REJEITADO' | 'REALIZADO';
  cidade: string;
  tags: string[];
}

@Component({
  selector: 'app-gestao-voluntarios',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HeaderComponent,
    FooterComponent,
    ModalAvaliarVoluntarioComponent
  ],
  templateUrl: './gestao-voluntarios.component.html',
  styleUrls: ['./gestao-voluntarios.component.scss'],
})
export class GestaoVoluntariosComponent implements OnInit {
  // Lista de voluntários carregados para a atividade
  v: VoluntarioHistoricoResponseDTO[] = [];
  // ID da atividade atual (obtido da rota)
  atividadeId!: number;
  // Página atual da paginação
  currentPage = 1;
  // Quantidade de voluntários por página
  pageSize = 5;
  showAvaliarModal = false;
  // Calcula o total de páginas para a paginação
  get totalPages(): number {
    return Math.ceil(this.v.length / this.pageSize);
  }

  // Gera um array com os números das páginas para o controle de paginação
  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  // Retorna apenas os voluntários da página atual
  get paginatedVoluntarios(): VoluntarioHistoricoResponseDTO[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.v.slice(start, start + this.pageSize);
  }

  // Injeta dependências: rota e serviço de voluntários
  constructor(
    private route: ActivatedRoute,
    private service: GestaoVoluntarioService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      console.log('ROUTE PARAMS:', params.keys, params);
      const raw = params.get('id'); // ← pega o 'id' em vez de 'atividadeId'
      if (!raw) {
        console.error('nenhum ID de atividade na URL');
        return;
      }
      this.atividadeId = +raw;
      this.carregarVoluntarios(this.atividadeId);
    });
  }

  // Ao inicializar o componente, pega o ID da atividade da rota e carrega os voluntários
  carregarVoluntarios(id: number) {
    this.service
      .carregarVoluntarios(this.atividadeId)
      .subscribe((inscritos: ListaInscritos[]) => {
        this.v = inscritos.map((i) => ({
          id: i.id,
          nomeCompleto: i.nomeCompleto,
          cidade: i.cidade,
          statusCandidatura: i.statusCandidatura as any,
          tags: i.tags,
        }));
      });
  }

  // Vai para a página anterior na paginação
  prevPage() {
    if (this.currentPage > 1) this.currentPage--;
  }
  // Vai para a próxima página na paginação
  nextPage() {
    if (this.currentPage < this.totalPages) this.currentPage++;
  }
  // Vai para uma página específica na paginação
  goToPage(n: number) {
    this.currentPage = n;
  }

  aprovar(volId: number): void {
    console.log('PARAMS this.atividadeId:', this.atividadeId);
    console.log('PARAMS volId:', volId);
    this.service
      .atualizarStatusCandidatura(this.atividadeId, volId, 'APROVADO')
      .subscribe(() => this.carregarVoluntarios(this.atividadeId));
  }

  rejeitar(volId: number): void {
    this.service
      .atualizarStatusCandidatura(this.atividadeId, volId, 'REJEITADO')
      .subscribe(() => this.carregarVoluntarios(this.atividadeId));
  }
  avaliar(volId: number): void {
    console.log('VOLUNTARIO A SER AVALIADO', volId);
    this.showAvaliarModal = true; // sinaliza para mostrar o modal
  }

  fecharModal(): void {
    this.showAvaliarModal = false;
  }
}
