import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IAtividade } from '../../model/atividade.model';
import { Tag } from '../../model/tag.model';
import { AtividadeService } from '../../service/atividade.service';
import { TagService } from '../../service/tag.service';
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
  tags: Tag[] = [];
  estados: string[] = [];
  filtroTag: string = '';
  filtroEstado: string = '';

  constructor(private atividadeService: AtividadeService, private tagService: TagService) {}

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

    this.tagService.buscarTodos().subscribe({
      next: (res) => (this.tags = res),
      error: (err) => console.error('Erro ao buscar tags:', err),
    });
  }

  // retorna a lista filtrada com base no termo de busca
  get atividadesFiltradas() {
    let lista = this.atividades;

    if (this.busca?.trim()) {
      const termo = this.busca.trim().toLowerCase();
      lista = lista.filter(
        (a) => a.nome.toLowerCase().includes(termo) || a.descricao.toLowerCase().includes(termo),
      );
    }

    if (this.filtroTag) {
      lista = lista.filter((a) => a.titulo === this.filtroTag);
    }

    return lista;
  }
  // registra a participação em uma atividade passando o ID da atividade para o endpoint
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

  filtrarAtividades() {
    this.paginaAtual = 1;
    this.atividadeService.buscarAtividadesPorTag(this.filtroTag).subscribe({
      next: (res) => {
        this.atividades = res.content;
      },
      error: (err) => {
        console.error('Erro ao filtrar atividades:', err);
      },
    });
  }
}
