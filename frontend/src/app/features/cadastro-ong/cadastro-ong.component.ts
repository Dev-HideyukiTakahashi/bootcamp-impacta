
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { NgxMaskDirective } from 'ngx-mask';
import { Router } from '@angular/router';
import { OngService } from '../../service/ong.service';

@Component({
  selector: 'app-cadastro-ong',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent, FooterComponent, NgxMaskDirective],
  templateUrl: './cadastro-ong.component.html',
  styleUrls: ['./cadastro-ong.component.scss']
})
export class CadastroOngComponent {
  fb = inject(FormBuilder);
  OngService: OngService = inject(OngService);
  router = inject(Router)
  showModal: boolean = false;
  http = inject(HttpClient);

  msg: string | null = null;
  erro: string | null = null;

  form: FormGroup = this.fb.group({
    nome: ['', Validators.required],
    email: ['', [Validators.required, Validators.pattern(/^\S[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\.)+[a-zA-Z]{2,}$/)]],
    cnpj: ['', [Validators.required, Validators.pattern(/^\d{14}$/)]],
    telefone: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
    cep: ['', [Validators.required, Validators.pattern(/^\d{5}-?\d{3}$/)]],
    endereco: [''],
    senha: ['', [Validators.required, Validators.minLength(6)]],
    confirmarSenha: ['', Validators.required]
  },
    { validators: this.senhaConfirmadaValidator() }
  );

  buscarEndereco() {
    const cep = this.form.value.cep?.replace(/\D/g, '');
    if (!cep || cep.length !== 8) return;

    this.http.get(`https://viacep.com.br/ws/${cep}/json/`).subscribe((dados: any) => {
      if (dados?.erro) return;
      this.form.patchValue({ endereco: `${dados.logradouro}, ${dados.bairro}, ${dados.localidade} - ${dados.uf}` });
    });
  }

  senhaConfirmadaValidator(): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const senha = group.get('senha')?.value;
      const confirmar = group.get('confirmarSenha')?.value;
      return senha === confirmar ? null : { senhasDiferentes: true };
    };
  }

  submit() {
    if (this.form.invalid) return;

    const payload = {
      nomeOng: this.form.value.nome.trim(),
      email: this.form.value.email.trim(),
      cnpj: this.form.value.cnpj.replace(/\D/g, ''),
      telefone: this.form.value.telefone.replace(/\D/g, ''),
      endereco: this.form.value.endereco.trim(),
      dataNascimento: this.form.value.dataNascimento,
      senha: this.form.value.senha
    };

    this.OngService.cadastrarOng(payload).subscribe({
      next: () => {
        this.msg = 'Cadastro realizado com sucesso!';
        this.erro = null;
        this.form.reset();
        this.showModal = true;

        setTimeout(() => this.router.navigate(['/login']), 1000);
      },
      error: (err) => {
        this.msg = null;
        if (err.status === 409) {
          this.erro = err.error?.message || 'Email ou CNPJ já cadastrado.';
        } else {
          this.erro = 'Erro ao cadastrar ONG.';
        }
        this.showModal = true;
      }
    });
  }
  fecharModal() {
    this.showModal = false;
    this.erro = null;
    this.msg = null;
  }

}
