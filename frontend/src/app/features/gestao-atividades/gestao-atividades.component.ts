import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // IMPORTANTE
import { RouterModule } from '@angular/router';
import { AtividadeService } from '../../service/atividade.service';
import { Atividade } from '../../model/atividade.model';
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
    HeaderComponent,
    FooterComponent
  ]
})
export class GestaoAtividadesComponent implements OnInit {
  atividades: Atividade[] = [];

  constructor(private atividadeService: AtividadeService) {}

  ngOnInit(): void {
    this.atividadeService.getAtividades().subscribe({
      next: lista => this.atividades = lista,
      error: err => console.error('Erro ao buscar atividades', err)
    });
  }
}
