import { CommonModule } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {
  AfterViewInit,
  Component,
  inject,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
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
import { Subject, takeUntil } from 'rxjs';
import { VoluntarioService } from '../../service/voluntario.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { ModalMensagemComponent } from '../../shared/modal-mensagem/modal-mensagem.component';

@Component({
  selector: 'app-cadastro-voluntario',
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
  templateUrl: './cadastro-voluntario.component.html',
  styleUrls: ['./cadastro-voluntario.component.scss'],
})
export class CadastroVoluntarioComponent
  implements OnInit, AfterViewInit, OnDestroy
{
  @ViewChild(ModalMensagemComponent) modalMensagem!: ModalMensagemComponent;

  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);
  private voluntarioService = inject(VoluntarioService);

  private destroy$ = new Subject<void>();

  public buscandoCep = false;

  public form: FormGroup = this.fb.group(
    {
      nomeCompleto: [
        '',
        [Validators.required, Validators.pattern(/^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/)],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^\S[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\.)+[a-zA-Z]{2,}$/
          ),
        ],
      ],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      dataNascimento: ['', [Validators.required, this.maiorDeIdadeValidator]],
      pais: ['Brasil', [Validators.required]],
      cep: ['', [Validators.required, Validators.pattern(/^\d{5}-?\d{3}$/)]],
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      bairro: ['', Validators.required],
      rua: ['', Validators.required],
      numero: ['', [Validators.required, Validators.pattern(/^\d{1,5}$/)]],
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

  public permitirSomenteLetras(event: KeyboardEvent): void {
    const tecla = event.key;
    const input = event.target as HTMLInputElement;
    const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ]$/;
    const teclasControle = [
      'Backspace',
      'ArrowLeft',
      'ArrowRight',
      'Tab',
      'Delete',
    ];

    if (teclasControle.includes(tecla) || event.ctrlKey || event.metaKey)
      return;

    if (tecla === ' ') {
      const valor = input.value;
      const cursor = input.selectionStart || 0;
      if (cursor === 0 || valor[cursor - 1] === ' ' || valor[cursor] === ' ') {
        event.preventDefault();
      }
      return;
    }

    if (!regex.test(tecla)) {
      event.preventDefault();
    }
  }

  public maiorDeIdadeValidator(
    control: AbstractControl
  ): ValidationErrors | null {
    const v = control.value;
    if (!v) {
      return null;
    }

    const nasc = new Date(v);
    if (isNaN(nasc.getTime())) {
      return { dataInvalida: true };
    }

    const ano = nasc.getFullYear();
    // Pessoa mais velha do mundo é de 1907
    if (ano < 1907 || ano > new Date().getFullYear()) {
      return { anoForaIntervalo: true };
    }

    const hoje = new Date();
    let idade = hoje.getFullYear() - ano;
    const mDiff = hoje.getMonth() - nasc.getMonth();
    const dDiff = hoje.getDate() - nasc.getDate();
    if (mDiff < 0 || (mDiff === 0 && dDiff < 0)) {
      idade--;
    }

    if (idade < 18) {
      return { menorDeIdade: true };
    }

    return null;
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
      nomeCompleto: this.form.value.nomeCompleto.trim(),
      cpf: this.form.value.cpf.replace(/\D/g, ''),
      telefone: this.form.value.telefone.replace(/\D/g, ''),
      dataNascimento: this.form.value.dataNascimento,
      email: this.form.value.email.trim(),
      senha: this.form.value.senha,
      endereco: {
        pais: this.form.value.pais,
        estado: this.form.value.estado,
        cidade: this.form.value.cidade,
        cep: this.form.value.cep,
        rua: this.form.value.rua,
        numero: this.form.value.numero,
        bairro: this.form.value.bairro,
      },
    };

    this.voluntarioService
      .cadastrarVoluntario(payload)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.modalMensagem.abrirSucesso('Cadastro realizado com sucesso!');
          this.form.reset();
        },
        error: (err: HttpErrorResponse) => {
          const backendMsg =
            err.error && (err.error as any).message
              ? (err.error as any).message
              : 'Erro ao cadastrar voluntário. Tente novamente.';
          this.modalMensagem.abrirErro(backendMsg);
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
