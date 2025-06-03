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
import { NgxMaskDirective } from 'ngx-mask';

import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { AtividadeService } from '../../service/atividade.service';

@Component({
  selector: 'app-cadastro-atividade',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,        // para [routerLink], se usar
    NgxMaskDirective,    // se precisar de máscara (phone, etc.)
    HeaderComponent,
    FooterComponent,
  ],
  templateUrl: './cadastro-atividade.component.html',
  styleUrls: ['./cadastro-atividade.component.scss'],
})
export class CadastroAtividadeComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private service = inject(AtividadeService);

  // Para controlar exibição de modal de sucesso/erro
  showModal: boolean = false;
  msg: string | null = null;
  erro: string | null = null;
  success = false;

  // O FormGroup com todos os campos
  form!: FormGroup;

  ngOnInit() {
    this.form = this.fb.group({
      // Campos antigos:
      nome: ['', Validators.required],
      periodo: ['', Validators.required],
      cargaHorariaDiaria: [
        '',
        [Validators.required, Validators.min(1)],
      ],
      enderecoCompleto: ['', Validators.required],
      possuiCertificacao: [null, Validators.required],
      descricao: ['', Validators.required],

      // ============================
      // Campos novos (para encaixar no mesmo payload)
      // ============================
      statusAtividade: ['', Validators.required],
      dataAtividade: ['', Validators.required],
      idTag: ['', [Validators.required, Validators.min(1)]],
    });
  }

  submit() {
    if (this.form.invalid) {
      // marca todos como “touched” para exibir mensagens de validação
      this.form.markAllAsTouched();
      return;
    }

    // Monta o payload com exatamente as chaves que o service espera
    const valorForm = this.form.value;

    // Convertendo data (string do input “datetime-local”) para ISO
    // Se seu backend aceita string ISO direta, transforme-a assim:
    const isoData = new Date(valorForm.dataAtividade).toISOString();

    const payload = {
      nome: valorForm.nome,
      periodo: valorForm.periodo,
      cargaHorariaDiaria: Number(valorForm.cargaHorariaDiaria),
      enderecoCompleto: valorForm.enderecoCompleto,
      possuiCertificacao: valorForm.possuiCertificacao,
      descricao: valorForm.descricao,
      statusAtividade: valorForm.statusAtividade,      // 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA'
      dataAtividade: isoData,
      idTag: Number(valorForm.idTag),
    };

    this.service.cadastrarAtividade(payload).subscribe({
      next: () => {
        this.success = true;
        this.msg = 'Cadastro realizado com sucesso!';
        this.erro = null;
        this.form.reset();
        this.showModal = true;

        // Se quiser redirecionar após X segundos, descomente:
        // setTimeout(() => this.router.navigate(['/alguma-rota']), 1500);
      },
      error: (err) => {
        this.msg = null;
        if (err.status === 409) {
          this.erro =
            err.error?.message ||
            'Algum dado já existe no sistema (conflito).';
        } else {
          this.erro = 'Erro ao cadastrar atividade.';
        }
        this.showModal = true;
      },
    });
  }
}
