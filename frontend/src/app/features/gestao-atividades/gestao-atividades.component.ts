// src/app/features/gestao-atividades/gestao-atividades.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // necessário para [(ngModel)] no <select>
import { AtividadeService } from '../../service/atividade.service';
import {
  GestaoVoluntarioService,
  ListaAprovados,
} from '../../service/gestaoVoluntario.service';
import { Atividade, StatusAtividade } from '../../model/atividade.model';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-gestao-atividades',
  standalone: true,
  templateUrl: './gestao-atividades.component.html',
  styleUrls: ['./gestao-atividades.component.scss'],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HeaderComponent,
    FooterComponent,
  ],
})
export class GestaoAtividadesComponent implements OnInit {
  // Lista de atividades carregadas do backend
  atividades: Atividade[] = [];
  // Opções de status possíveis para uma atividade
  statusOptions: StatusAtividade[] = ['ANDAMENTO', 'ENCERRADA', 'CONGELADA'];
  // ID da atividade cujo histórico de voluntários está aberto no modal
  historicoOpenId: number | null = null;
  // Objeto que armazena o histórico de voluntários aprovados por atividade
  historicos: { [id: number]: ListaAprovados } = {};

  // Injeta os serviços necessários: atividades, roteamento e voluntários
  constructor(
    private atividadeService: AtividadeService,
    private router: Router,
    private gestaoVoluntarioService: GestaoVoluntarioService
  ) {}

  // Ao inicializar o componente, carrega a lista de atividades
  ngOnInit(): void {
    this.loadAtividades();
  }

  // Busca as atividades no backend e atualiza a lista local
  loadAtividades(): void {
    this.atividadeService.getAtividades().subscribe({
      next: (lista) => (this.atividades = lista),
      error: (err) => console.error('Erro ao carregar atividades', err),
    });
  }

  // Converte o valor do status para um texto mais amigável para exibição
  labelStatus(status: StatusAtividade): string {
    switch (status) {
      case 'ANDAMENTO':
        return 'Em andamento';
      case 'ENCERRADA':
        return 'Encerrada';
      case 'CONGELADA':
        return 'Atividade congelada';
      default:
        return status;
    }
  }

  // Navega para a tela de edição da atividade selecionada
  editarAtividade(atividade: Atividade): void {
    this.router.navigate(['/atividades/editar', atividade.id]);
  }

  // Atualiza o status da atividade para "CONGELADA" ao clicar em "Congelar"
  congelarAtividade(atividade: Atividade): void {
    this.atividadeService.atualizarStatus(atividade.id, 'CONGELADA').subscribe({
      next: (updated) => (atividade.statusAtividade = updated.statusAtividade),
      error: (err) => console.error('Erro ao congelar atividade', err),
    });
  }

  // Atualiza o status da atividade para "ANDAMENTO" ao clicar em "Reabrir Atividade"
  reabrirAtividade(atividade: Atividade): void {
    this.atividadeService.atualizarStatus(atividade.id, 'ANDAMENTO').subscribe({
      next: () => (atividade.statusAtividade = 'ANDAMENTO'),
      error: (err) => console.error('Erro ao reabrir atividade', err),
    });
  }

  // Navega para a tela de gestão de voluntários da atividade selecionada
  listarVoluntarios(atividade: Atividade): void {
    this.router.navigate(['/gestao-voluntarios'], {
      state: { atividadeId: atividade.id },
    });
  }

  // Navega para a tela de cadastro de nova atividade
  novaAtividade(): void {
    this.router.navigate(['/cadastro-atividade']);
  }

  /**
   * Busca os voluntários aprovados para a atividade e abre o modal de histórico.
   * Chamada ao clicar em "Histórico de Voluntários".
   */
  listaVoluntariosAprovados(atividade: Atividade): void {
    console.log('Buscando voluntarios da atividade:', atividade.id);
    this.gestaoVoluntarioService
      .getVoluntariosAprovados(atividade.id)
      .subscribe({
        next: (data) => {
          // Salva o histórico retornado e abre o modal para a atividade selecionada
          this.historicos[atividade.id] = data;
          this.historicoOpenId = atividade.id;
        },
        error: (err) => {
          console.error('Erro ao buscar voluntarios aprovados', err);
        },
      });
  }

  // Fecha o modal de histórico de voluntários
  fecharHistorico(): void {
    this.historicoOpenId = null;
  }
}
