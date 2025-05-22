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
    RouterModule,        // para [routerLink]
    NgxMaskDirective,    // se precisar de máscara
    HeaderComponent,
    FooterComponent
  ],
  templateUrl: './cadastro-atividade.component.html',
  styleUrls: ['./cadastro-atividade.component.scss'],
  hostDirectives: [],
  // Protege rota se você usar provideRouter direto no componente (v17+)
  // Se estiver usando app.routes.ts, basta configurar lá.
})
export class CadastroAtividadeComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private service = inject(AtividadeService);
  // para erro
  showModal: boolean = false;
  msg: string | null = null;
  erro: string | null = null;
  success = false;  form!: FormGroup;
  errorMessage: string | null = null;

  ngOnInit() {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      periodo: ['', Validators.required],
      cargaHorariaDiaria: ['', Validators.required],
      enderecoCompleto: ['', Validators.required],
      possuiCertificacao: [null, Validators.required],
      descricao: ['', Validators.required],
    });
  }

  submit() {
    if (this.form.invalid) return;
    const payload = this.form.value;
    this.service.cadastrarAtividade(this.form.value).subscribe({
      next: () => {
        this.success = true;
        this.msg = 'Cadastro realizado com sucesso!';
        this.erro = null;
        this.form.reset();
        this.showModal = true;
        // opcional: navegue após X segundos:
         setTimeout(() => this.router.navigate(['/cadastro-atividade']), 1500);
      },
     error: (err) => {
        this.msg = null;
        if (err.status === 409) {
          this.erro = err.error?.message || 'Email ou CPF já cadastrado.';
        } else {
          this.erro = 'Erro ao cadastrar voluntário.';
        }
        this.showModal = true;
      },
    });
  }
}
