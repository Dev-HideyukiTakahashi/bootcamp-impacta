// src/app/features/gestao-atividades/gestao-atividades.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // necessário para [(ngModel)] no <select>

import { AtividadeService } from '../../service/atividade.service';
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
  ]
})
export class GestaoAtividadesComponent implements OnInit {
  atividades: Atividade[] = [];
  statusOptions: StatusAtividade[] = ['ANDAMENTO', 'ENCERRADA', 'CONGELADA'];

  constructor(
    private atividadeService: AtividadeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAtividades();
  }

  loadAtividades(): void {
    this.atividadeService.getAtividades().subscribe({
      next: lista => (this.atividades = lista),
      error: err => console.error('Erro ao carregar atividades', err)
    });
  }

  /** Converte valor do enum em texto legível */
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

  /** Chamado ao clicar em “Editar” */
  editarAtividade(atividade: Atividade): void {
    this.router.navigate(['/atividades/editar', atividade.id]);
  }

  /** Chamado ao clicar em “Congelar” */
  congelarAtividade(atividade: Atividade): void {
    this.atividadeService.atualizarStatus(atividade.id, 'CONGELADA').subscribe({
      next: () => (atividade.statusAtividade = 'CONGELADA'),
      error: err => console.error('Erro ao congelar atividade', err)
    });
  }

  /** Chamado ao clicar em “Reabrir Atividade” */
  reabrirAtividade(atividade: Atividade): void {
    this.atividadeService.atualizarStatus(atividade.id, 'ANDAMENTO').subscribe({
      next: () => (atividade.statusAtividade = 'ANDAMENTO'),
      error: err => console.error('Erro ao reabrir atividade', err)
    });
  }

  /** Chamado ao clicar em “Buscar Voluntários” */
  buscarVoluntarios(atividade: Atividade): void {
    this.router.navigate(['/atividades', atividade.id, 'voluntarios']);
  }

  /** Chamado ao clicar em “Histórico de Voluntários” */
  historicoVoluntarios(atividade: Atividade): void {
    this.router.navigate(['/atividades', atividade.id, 'historico-voluntarios']);
  }

  /** Botão “Nova Atividade” */
  novaAtividade(): void {
    this.router.navigate(['/cadastro-atividade']);
  }
}
