import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HistoricoAtividade } from '../../model/historico-atividade.model';
import { HistoricoAtividadeService } from '../../service/historico-atividade.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
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
    this.carregarAtividadesRealizadas();
  }

  carregarAtividadesRealizadas(): void {
    this.historicoService.getAtividadesRealizadas().subscribe({
      next: (atividades) => (this.atividadesRealizadas = atividades),
      error: (err) => console.error('Erro ao buscar atividades realizadas:', err),
    });
  }
}
