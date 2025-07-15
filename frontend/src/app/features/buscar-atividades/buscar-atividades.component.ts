import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IAtividade } from '../../model/atividade.model';
import { Tag } from '../../model/tag.model';
import { AtividadeService } from '../../service/atividade.service';
import { TagService } from '../../service/tag.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

type StatusCandidatura = 'APROVADO' | 'REJEITADO' | 'REALIZADO' | 'PENDENTE';

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
  tags: Tag[] = [];
  estados: string[] = [];
  filtroTag: string = '';
  filtroEstado: string = '';

  filtrosStatus = {
    APROVADO: false,
    REJEITADO: false,
    REALIZADO: false,
  };

  constructor(private atividadeService: AtividadeService, private tagService: TagService) {}

  ngOnInit() {
    this.atividadeService.getBuscarAtividade().subscribe({
      next: (res) => {
        this.atividades = res.content;
      },
      error: (err) => {
        console.error('Erro ao buscar atividades:', err);
      },
    });

    this.tagService.buscarTodos().subscribe({
      next: (res) => (this.tags = res),
      error: (err) => console.error('Erro ao buscar tags:', err),
    });
  }

  get atividadesFiltradas(): IAtividade[] {
    let lista = [...this.atividades];

    // Filtro por cidade/estado (enderecoCompleto)
    if (this.busca?.trim()) {
      const termo = this.normalizarTexto(this.busca);
      lista = lista.filter((a) => this.normalizarTexto(a.enderecoCompleto).includes(termo));
    }

    // Filtro por Tag
    if (this.filtroTag) {
      lista = lista.filter((a) => a.titulo === this.filtroTag);
    }

    // Filtro por Status de Candidatura
    const statusSelecionados = Object.entries(this.filtrosStatus)
      .filter(([_, checked]) => checked)
      .map(([status]) => status);

    if (statusSelecionados.length > 0) {
      lista = lista.filter((a) =>
        statusSelecionados.includes(a.statusCandidatura as StatusCandidatura),
      );
    } else {
      // Exibe apenas atividades com statusCandidatura === null
      lista = lista.filter((a) => a.statusCandidatura == null);
    }

    return lista;
  }

  aplicarFiltros(): void {
    this.paginaAtual = 1;
  }

  participar(atividadeId: number) {
    this.atividadeService.atualizarCandidatura(atividadeId).subscribe({
      next: () => {
        this.ngOnInit();
      },
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

  private normalizarTexto(texto: string): string {
    return texto
      .normalize('NFD') // separa acentos das letras
      .replace(/[\u0300-\u036f]/g, '') // remove acentos
      .toLowerCase()
      .trim();
  }
}
