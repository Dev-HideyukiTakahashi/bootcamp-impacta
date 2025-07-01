import { Component } from '@angular/core';
import { AtividadeService } from '../../service/atividade.service';
import { Atividade } from '../../model/atividade.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../../shared/footer/footer.component';
import { HeaderComponent } from '../../shared/components/header/header.component';

@Component({
  selector: 'app-buscar-atividades',
  standalone: true,
  imports: [CommonModule, FormsModule, FooterComponent, HeaderComponent],
  templateUrl: './buscar-atividades.component.html',
  styleUrls: ['./buscar-atividades.component.scss']
})
export class BuscarAtividadesComponent {
  atividades: Atividade[] = [];
  busca: string = '';
  constructor(private atividadeService: AtividadeService) { }

  ngOnInit() {
    // Faz a requisição para buscar as atividades na API
    this.atividadeService.getBuscarAtividade().subscribe({
      next: (res) => {
        this.atividades = res.content;
      },
      error: (err) => {
        console.error('Erro ao buscar atividades:', err);
      }
    });
  }
  // retorna a lista filtrada com base no termo de busca
  get atividadesFiltradas() {
    if (!this.busca?.trim()) return this.atividades;
    const termo = this.busca.trim().toLowerCase();
    return this.atividades.filter(a =>
      a.nome.toLowerCase().includes(termo) ||
      a.descricao.toLowerCase().includes(termo)
    );
  }
  // registra a participação em uma atividade passando o ID da atividade para o endpoint
  participar(atividadeId: number) {
  this.atividadeService.atualizarCandidatura(atividadeId).subscribe({
    error: (err) => {
      alert('Erro ao participar da atividade!');
      console.error(err);
    }
  });
}
}