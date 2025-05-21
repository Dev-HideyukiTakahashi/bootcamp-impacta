import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { NgxMaskDirective } from 'ngx-mask';
import { Router } from '@angular/router';
import { VoluntarioService } from '../../service/voluntario.service';

@Component({
  selector: 'app-cadastro-voluntario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HeaderComponent, FooterComponent, NgxMaskDirective],
  templateUrl: './cadastro-voluntario.component.html',
  styleUrl: './cadastro-voluntario.component.scss'
})
export class CadastroVoluntarioComponent {
  fb = inject(FormBuilder);
  router = inject(Router)
  showModal: boolean = false;
  voluntarioService: VoluntarioService = inject(VoluntarioService);

  msg: string | null = null;
  erro: string | null = null;

  form: FormGroup = this.fb.group(
    {
      nome: ['', [Validators.required, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/)]],
      email: ['', [Validators.required, Validators.pattern(/^\S[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\.)+[a-zA-Z]{2,}$/)]],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      dataNascimento: ['', [Validators.required, this.maiorDeIdadeValidator]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      confirmarSenha: ['', Validators.required]
  },
    { validators: this.senhaConfirmadaValidator() }
  );

  permitirSomenteLetras(event: KeyboardEvent): void {
    const tecla = event.key;
    const input = event.target as HTMLInputElement;

    const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ]$/; // Apenas letras (sem espaço)
    const teclasControle = ['Backspace', 'ArrowLeft', 'ArrowRight', 'Tab', 'Delete'];

    if (teclasControle.includes(tecla) || event.ctrlKey || event.metaKey) return;

    if (tecla === ' ') {
      const valor = input.value;
      const cursor = input.selectionStart || 0;

      // Bloqueia espaço no início ou se já houver espaço antes
      if (cursor === 0 || valor[cursor - 1] === ' ' || valor[cursor] === ' ') {
        event.preventDefault();
      }
      return;
    }

    if (!regex.test(tecla)) {
      event.preventDefault();
    }
  }

  maiorDeIdadeValidator(control: AbstractControl) {
    const nascimento = new Date(control.value);
    const hoje = new Date();
    const idade = hoje.getFullYear() - nascimento.getFullYear();
    if (idade < 18 || (idade === 18 && hoje < new Date(nascimento.setFullYear(nascimento.getFullYear() + 18)))) {
      return { menorDeIdade: true };
    }
    return null;
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
      nomeCompleto: this.form.value.nome.trim(),
      email: this.form.value.email.trim(),
      cpf: this.form.value.cpf.replace(/\D/g, ''),
      telefone: this.form.value.telefone.replace(/\D/g, ''),
      dataNascimento: this.form.value.dataNascimento,
      senha: this.form.value.senha
    };

    this.voluntarioService.cadastrarVoluntario(payload).subscribe({
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
          this.erro = err.error?.message || 'Email ou CPF já cadastrado.';
        } else {
          this.erro = 'Erro ao cadastrar voluntário.';
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
