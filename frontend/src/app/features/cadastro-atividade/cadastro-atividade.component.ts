// src/app/features/cadastro-atividade/cadastro-atividade.component.ts

import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
  FormGroup,
} from '@angular/forms';
import { RouterModule, Router } from '@angular/router';

import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { AtividadeService } from '../../service/atividade.service';
import { TagService } from '../../service/tag.service';
import { Tag } from '../../model/tag.model';

@Component({
  selector: 'app-cadastro-atividade',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    HeaderComponent,
    FooterComponent,
  ],
  templateUrl: './cadastro-atividade.component.html',
  styleUrls: ['./cadastro-atividade.component.scss'],
})
export class CadastroAtividadeComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private atividadeService = inject(AtividadeService);
  private tagService = inject(TagService);

  form!: FormGroup;
  tags: Tag[] = [];

  showModal = false;
  msg: string | null = null;
  erro: string | null = null;
  success = false;

  ngOnInit() {
    // 1) Inicializa o FormGroup
    this.form = this.fb.group({
      nome: ['', Validators.required],
      periodo: ['', Validators.required],
      cargaHorariaDiaria: ['', [Validators.required, Validators.min(1)]],
      enderecoCompleto: ['', Validators.required],
      possuiCertificacao: [null, Validators.required],
      descricao: ['', Validators.required],
      statusAtividade: ['', Validators.required],
      dataAtividade: ['', Validators.required],
      idTag: [null, Validators.required],
    });

    // 2) Carrega as tags disponíveis
    this.tagService.buscarTodos().subscribe({
      next: buscarTodos => {
        this.tags = buscarTodos;
        // Opcional: se quiser definir valor padrão, pode setar aqui
        // this.form.get('idTag')!.setValue(this.tags[0].id);
      },
      error: err => console.error('Erro ao carregar lista de tags', err),
    });
  }

  /**
   * Chamado quando o usuário muda a seleção de tag.
   * Busca o objeto Tag completo (com id e nome) e copia o nome para o campo 'nome' da atividade.
   */
  onTagChange(event: Event) {
    const selectEl = event.target as HTMLSelectElement;
    const tagIdSelecionada = Number(selectEl.value);
    // Encontre o objeto Tag completo no array
    const tagObj = this.tags.find(t => t.id === tagIdSelecionada);
    if (tagObj) {
      // Atribui o nome da tag ao campo 'nome' do formulário
      this.form.get('nome')!.setValue(tagObj.nome);
    } else {
      // Se desmarcado, limpa o nome
      this.form.get('nome')!.setValue('');
    }
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const valorForm = this.form.value;
    const isoData = new Date(valorForm.dataAtividade).toISOString();

    const payload = {
      nome: valorForm.nome,
      periodo: valorForm.periodo,
      cargaHorariaDiaria: Number(valorForm.cargaHorariaDiaria),
      enderecoCompleto: valorForm.enderecoCompleto,
      possuiCertificacao: valorForm.possuiCertificacao,
      descricao: valorForm.descricao,
      statusAtividade: valorForm.statusAtividade,
      dataAtividade: isoData,
      idTag: Number(valorForm.idTag),
    };

    this.atividadeService.cadastrarAtividade(payload).subscribe({
      next: () => {
        this.success = true;
        this.msg = 'Cadastro realizado com sucesso!';
        this.erro = null;
        this.form.reset();
        this.showModal = true;
        setTimeout(() => this.router.navigate(['/atividades']), 1500);
      },
      error: err => {
        this.msg = null;
        this.erro =
          err.status === 409
            ? err.error?.message || 'Conflito de dados.'
            : 'Erro ao cadastrar atividade.';
        this.showModal = true;
      },
    });
  }
}
