import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { NgxMaskDirective } from 'ngx-mask';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-cadastro-ong',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgxMaskDirective,
    HeaderComponent,
    FooterComponent,
    ModalMensagemComponent,
  ],
  templateUrl: './cadastro-ong.component.html',
  styleUrls: ['./cadastro-ong.component.scss'],
})
export class CadastroOngComponent {
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);
  showModal: boolean = false;

  msg: string | null = null;
  erro: string | null = null;

  form: FormGroup = this.fb.group(
    {
      nome: ['', Validators.required],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^\S[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\.)+[a-zA-Z]{2,}$/
          ),
        ],
      ],
      cnpj: ['', [Validators.required, Validators.pattern(/^\d{14}$/)]],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      cep: ['', [Validators.required, Validators.pattern(/^\d{5}-?\d{3}$/)]],
      endereco: [''],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      confirmarSenha: ['', Validators.required],
    },
    { validators: this.senhaConfirmadaValidator() }
  );

  ngOnInit(): void {
    this.form
      .get('cep')!
      .valueChanges.pipe(takeUntil(this.destroy$))
      .subscribe((valor) => {
        const cep = (valor || '').replace(/\D/g, '');
        this.form.patchValue(
          { estado: '', cidade: '', bairro: '', rua: '' },
          { emitEvent: false }
        );
        if (cep.length === 8) {
          this.buscarEndereco();
        }
      });
  }

  ngAfterViewInit(): void {
    this.modalMensagem.fechado
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.onModalFechado());
  }

  public buscarEndereco(): void {
    const raw = this.form.get('cep')!.value || '';
    const cep = raw.replace(/\D/g, '');
    if (!/^\d{8}$/.test(cep)) {
      this.form.get('cep')!.setErrors({ pattern: true });
      this.form.patchValue({
        rua: '',
        bairro: '',
        cidade: '',
        estado: '',
        numero: '',
      });
      return;
    }

    this.buscandoCep = true;
    this.http.get<any>(`https://viacep.com.br/ws/${cep}/json/`).subscribe({
      next: (dados) => {
        if (dados.erro) {
          this.form.get('cep')!.setErrors({ cepInvalido: true });
          this.form.patchValue({
            rua: '',
            bairro: '',
            cidade: '',
            estado: '',
            numero: '',
          });
        } else {
          this.form.patchValue({
            rua: dados.logradouro || '',
            bairro: dados.bairro || '',
            cidade: dados.localidade || '',
            estado: dados.uf || '',
          });
          this.form.get('cep')!.setErrors(null);
        }
        this.buscandoCep = false;
      },
      error: () => {
        this.form.get('cep')!.setErrors({ cepInvalido: true });
        this.buscandoCep = false;
      },
    });
  }

  public permitirSomenteNumeros(event: KeyboardEvent): void {
    const teclasPermitidas = [
      'Backspace',
      'Tab',
      'ArrowLeft',
      'ArrowRight',
      'Delete',
    ];
    const isNumero = /^[0-9]$/.test(event.key);

    if (!isNumero && !teclasPermitidas.includes(event.key)) {
      event.preventDefault();
      return;
    }
    if (isNumero) {
      const input = event.target as HTMLInputElement;
      if (input.value.length >= 5) {
        event.preventDefault();
      }
    }
  }

  public senhaConfirmadaValidator(): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const senha = group.get('senha')?.value;
      const confirmar = group.get('confirmarSenha')?.value;
      return senha === confirmar ? null : { senhasDiferentes: true };
    };
  }

  public submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      nomeEntidade: this.form.value.nomeOng.trim(),
      cnpj: this.form.value.cnpj.replace(/\D/g, ''),
      telefone: this.form.value.telefone.replace(/\D/g, ''),
      cep: this.form.value.cep,
      endereco: this.form.value.endereco,
      senha: this.form.value.senha,
    };

    this.http.post('http://localhost:8080/ongs', payload).subscribe({
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
      },
    });
  }

  public onModalFechado() {
    if (this.modalMensagem.isSucesso) {
      this.router.navigate(['/login']);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
