import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IAtividade } from '../../model/atividade.model';
import { AtividadeService } from '../../service/atividade.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-buscar-atividades',
  standalone: true,
  imports: [CommonModule, FormsModule, FooterComponent, HeaderComponent],
  templateUrl: './buscar-atividades.component.html',
  styleUrls: ['./buscar-atividades.component.scss'],
})
export class BuscarAtividadesComponent {
  atividades: IAtividade[] = [];
  busca: string = '';
  paginaAtual: number = 1;
  itensPorPagina: number = 6;

  constructor(private atividadeService: AtividadeService) {}

  ngOnInit() {
    // Faz a requisição para buscar as atividades na API
    this.atividadeService.getBuscarAtividade().subscribe({
      next: (res) => {
        this.atividades = res.content;
      },
      error: (err) => {
        console.error('Erro ao buscar atividades:', err);
      },
    });
  }
  // retorna a lista filtrada com base no termo de busca
  get atividadesFiltradas() {
    if (!this.busca?.trim()) return this.atividades;
    const termo = this.busca.trim().toLowerCase();
    return this.atividades.filter(
      (a) => a.nome.toLowerCase().includes(termo) || a.descricao.toLowerCase().includes(termo),
    );
  }
  // registra a participação em uma atividade passando o ID da atividade para o endpoint
  participar(atividadeId: number) {
    this.atividadeService.atualizarCandidatura(atividadeId).subscribe({
      error: (err) => {
        alert('Erro ao participar da atividade!');
        console.error(err);
      },
    });
  }

  get atividadesPaginadas() {
    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    return this.atividadesFiltradas.slice(inicio, fim);
  }

  get totalPaginas(): number[] {
    const total = Math.ceil(this.atividadesFiltradas.length / this.itensPorPagina);
    return Array.from({ length: total }, (_, i) => i + 1);
  }
}
