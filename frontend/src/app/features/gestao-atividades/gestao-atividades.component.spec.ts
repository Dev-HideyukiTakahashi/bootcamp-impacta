// src/app/features/gestao-atividades/gestao-atividades.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // necessário para ngModel

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
    FormsModule,   // habilita [(ngModel)] no <select>
    HeaderComponent,
    FooterComponent,
  ]
})
export class GestaoAtividadesComponent implements OnInit {
  atividades: Atividade[] = [];

  // Para popular o <select> de status (ANDAMENTO, ENCERRADA, CONGELADA)
  statusOptions: StatusAtividade[] = ['ANDAMENTO', 'ENCERRADA', 'CONGELADA'];

  constructor(
    private atividadeService: AtividadeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAtividades();
  }

  // Carrega todas as atividades da ONG
  loadAtividades(): void {
    this.atividadeService.getAtividades().subscribe({
      next: (lista) => this.atividades = lista,
      error: (err) => console.error('Erro ao carregar atividades', err)
    });
  }

  // Converte o valor do enum de status em um texto legível
  labelStatus(status: StatusAtividade): string {
    switch (status) {
      case 'ANDAMENTO':
        return 'Em andamento';
      case 'ENCERRADA':
        return 'Encerrada';
      case 'CONGELADA':
        return 'Atividade Congelada';
      default:
        return status;
    }
  }

  // Chamado quando o usuário muda o select de status
  alterarStatus(atividade: Atividade, novoStatus: StatusAtividade): void {
    this.atividadeService.atualizarStatus(atividade.id, novoStatus).subscribe({
      next: () => {
        // Atualiza localmente para refletir imediatamente na UI
        atividade.statusAtividade = novoStatus;
      },
      error: err => console.error('Erro ao alterar status', err)
    });
  }

  // Navega para a tela de edição; você pode passar o ID como parâmetro
  editarAtividade(atividade: Atividade): void {
    this.router.navigate(['/atividades/editar', atividade.id]);
  }

  // Congela a atividade (muda o status para CONGELADA)
  congelarAtividade(atividade: Atividade): void {
    this.alterarStatus(atividade, 'CONGELADA');
  }

  // Reabre uma atividade congelada (muda o status para ANDAMENTO)
  reabrirAtividade(atividade: Atividade): void {
    this.alterarStatus(atividade, 'ANDAMENTO');
  }

  // Navega para a tela de “Buscar Voluntários” (pode ser /atividades/{id}/voluntarios)
  buscarVoluntarios(atividade: Atividade): void {
    this.router.navigate(['/atividades', atividade.id, 'voluntarios']);
  }

  // Navega para o histórico de voluntários daquela atividade
  historicoVoluntarios(atividade: Atividade): void {
    this.router.navigate(['/atividades', atividade.id, 'historico-voluntarios']);
  }

  // Botão “Nova Atividade” → navega para a página de cadastro
  novaAtividade(): void {
    this.router.navigate(['/cadastro-atividade']);
  }
}
