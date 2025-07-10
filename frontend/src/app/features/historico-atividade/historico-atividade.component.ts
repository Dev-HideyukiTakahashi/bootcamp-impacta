import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { HistoricoAtividade } from '../../model/historico-atividade.model';
import { HistoricoAtividadeService } from '../../service/historico-atividade.service';
@Component({
  selector: 'app-historico-atividade',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './historico-atividade.component.html',
  styleUrls: ['./historico-atividade.component.scss'],
})
export class HistoricoAtividadeComponent implements OnInit {
  atividadeAtual: HistoricoAtividade | null = null;
  atividadesRealizadas: HistoricoAtividade[] = [];

  constructor(private historicoService: HistoricoAtividadeService) {}

  ngOnInit(): void {
    this.carregarAtividadeAtual();
    this.carregarAtividadesRealizadas();
  }

  carregarAtividadeAtual(): void {
    this.historicoService.getAtividadeAtual().subscribe({
      next: (atividade) => (this.atividadeAtual = atividade),
      error: (err) => console.error('Erro ao buscar atividade atual:', err),
    });
  }

  carregarAtividadesRealizadas(): void {
    this.historicoService.getAtividadesRealizadas().subscribe({
      next: (atividades) => (this.atividadesRealizadas = atividades),
      error: (err) => console.error('Erro ao buscar atividades realizadas:', err),
    });
  }
}
