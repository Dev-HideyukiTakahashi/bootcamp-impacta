import { Component, OnInit, OnDestroy, AfterViewInit, ViewChild } from '@angular/core';
import {
  AbstractControl,
  ValidationErrors,
  ValidatorFn,
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { NgxMaskDirective } from 'ngx-mask';
import { Subject, takeUntil } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { VoluntarioService } from '../../service/voluntario.service';
import { CarregarDadosVoluntario } from '../../model/VoluntarioModel';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { ModalMensagemComponent } from '../../shared/modal-mensagem/modal-mensagem.component';

@Component({
  selector: 'app-editar-voluntario',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgxMaskDirective,
    HeaderComponent,
    FooterComponent,
    ModalMensagemComponent
  ],
  templateUrl: './editar-voluntario.component.html',
  styleUrls: ['./editar-voluntario.component.scss']
})
export class EditarVoluntarioComponent implements OnInit, OnDestroy, AfterViewInit {
  @ViewChild(ModalMensagemComponent) modalMensagem!: ModalMensagemComponent;

  perfil?: CarregarDadosVoluntario;
  perfilForm!: FormGroup;
  private destroy$ = new Subject<void>();
  salvarDados = false;
  buscandoCep = false;
  formAlterado = false;

  constructor(
    private voluntarioService: VoluntarioService,
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.perfilForm = this.fb.group({
      nomeCompleto: [{ value: '', disabled: true }],
      email: [{ value: '', disabled: true }],
      cpf: [{ value: '', disabled: true }],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      dataNascimento: [{ value: '', disabled: true }],
      pais: [{ value: '', disabled: true }],
      cep: ['', [Validators.required, Validators.pattern(/^\d{5}-?\d{3}$/)]],
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      bairro: ['', Validators.required],
      rua: ['', Validators.required],
      numero: ['', [Validators.required, Validators.pattern(/^\d{1,5}$/)]],
      alterarSenha: ['nao'],
      senha: [''],
      confirmarSenha: [''],
    }, { validators: this.senhasIguaisValidator() });

    this.carregarDados();

    this.perfilForm.get('cep')!.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe(valor => {
        const cep = (valor || '').replace(/\D/g, '');
        this.perfilForm.patchValue(
          { estado: '', cidade: '', bairro: '', rua: '' },
          { emitEvent: false }
        );
        if (cep.length === 8) {
          this.buscarEndereco();
        }
      });

    this.perfilForm.get('alterarSenha')!.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe(val => this.configurarValidatorsSenha(val));

    this.perfilForm.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.verificarAlteracoes());
  }

  ngAfterViewInit(): void {
    this.modalMensagem.fechado
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.onModalFechado());
  }

  private carregarDados(): void {
    this.voluntarioService.DadosVoluntario()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: dto => {
          this.perfil = dto;
          this.perfilForm.patchValue({
            nomeCompleto: dto.nomeCompleto,
            email: dto.email,
            cpf: dto.cpf,
            telefone: dto.telefone,
            dataNascimento: dto.dataNascimento?.split(' ')[0],
            pais: dto.pais,
            cep: dto.cep,
            estado: dto.estado,
            cidade: dto.cidade,
            bairro: dto.bairro,
            rua: dto.rua,
            numero: dto.numero,
            alterarSenha: 'nao',
            senha: '',
            confirmarSenha: ''
          });
          this.verificarAlteracoes();
        },
        error: err => {
          console.error('Erro ao carregar dados do voluntário:', err);
        }
      });
  }

  public buscarEndereco(): void {
    const raw = this.perfilForm.get('cep')!.value || '';
    const cep = raw.replace(/\D/g, '');
    if (!/^\d{8}$/.test(cep)) {
      this.perfilForm.get('cep')!.setErrors({ pattern: true });
      this.perfilForm.patchValue({ rua: '', bairro: '', cidade: '', estado: '', numero: '' });
      return;
    }
    this.buscandoCep = true;
    this.http.get<any>(`https://viacep.com.br/ws/${cep}/json/`)
      .subscribe({
        next: dados => {
          if (dados.erro) {
            this.perfilForm.get('cep')!.setErrors({ cepInvalido: true });
            this.perfilForm.patchValue({ rua: '', bairro: '', cidade: '', estado: '', numero: '' });
          } else {
            this.perfilForm.patchValue({
              rua: dados.logradouro || '',
              bairro: dados.bairro || '',
              cidade: dados.localidade || '',
              estado: dados.uf || ''
            });
            this.perfilForm.get('cep')!.setErrors(null);
          }
          this.buscandoCep = false;
        },
        error: () => {
          this.perfilForm.get('cep')!.setErrors({ cepInvalido: true });
          this.buscandoCep = false;
        }
      });
  }

  public permitirSomenteNumeros(event: KeyboardEvent): void {
    const teclasPermitidas = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete'];
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

  private configurarValidatorsSenha(val: string) {
    const s = this.perfilForm.get('senha')!;
    const c = this.perfilForm.get('confirmarSenha')!;
    if (val === 'sim') {
      s.setValidators([Validators.required, Validators.minLength(6)]);
      c.setValidators([Validators.required]);
    } else {
      s.clearValidators();
      c.clearValidators();
      this.perfilForm.patchValue({ senha: '', confirmarSenha: '' });
    }
    s.updateValueAndValidity();
    c.updateValueAndValidity();
  }

  private senhasIguaisValidator(): ValidatorFn {
    return (ctrl: AbstractControl): ValidationErrors | null => {
      if (ctrl.get('alterarSenha')!.value === 'sim') {
        const s = ctrl.get('senha')!.value;
        const c = ctrl.get('confirmarSenha')!.value;
        if (s && c && s !== c) {
          return { senhasDiferentes: true };
        }
      }
      return null;
    };
  }

  private verificarAlteracoes() {
    if (!this.perfil) { this.formAlterado = false; return; }
    const telAlt = (this.perfilForm.get('telefone')!.value || '').trim()
      !== (this.perfil!.telefone || '').trim();
    const endAlt = ['cep', 'estado', 'cidade', 'bairro', 'rua', 'numero']
      .some(f => (this.perfilForm.get(f)!.value || '').trim()
        !== (this.perfil![f as keyof CarregarDadosVoluntario] || '').trim());
    const senAlt = this.perfilForm.get('alterarSenha')!.value === 'sim';
    this.formAlterado = telAlt || endAlt || senAlt;
  }


  formatarCPF(cpf?: string): string {
    if (!cpf) return '';
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }

  salvar(): void {
    if (this.perfilForm.invalid) {
      this.perfilForm.markAllAsTouched();
      return;
    }
    if (!this.formAlterado) {
      this.modalMensagem.abrirErro('Nenhuma alteração detectada.');
      return;
    }

    const payload: any = {};
    if ((this.perfilForm.get('telefone')!.value || '').trim()
      !== (this.perfil!.telefone || '').trim()) {
      payload.telefone = this.perfilForm.get('telefone')!.value;
    }

    const camposEnd = ['cep', 'estado', 'cidade', 'bairro', 'rua', 'numero'];
    const endAlt = camposEnd.some(c =>
      (this.perfilForm.get(c)!.value || '').trim()
      !== (this.perfil![c as keyof CarregarDadosVoluntario] || '').trim()
    );
    if (endAlt) {
      payload.endereco = {};
      camposEnd.forEach(c => {
        const val = this.perfilForm.get(c)!.value;
        if ((val || '').trim()
          !== (this.perfil![c as keyof CarregarDadosVoluntario] || '').trim()) {
          (payload.endereco as any)[c] = val;
        }
      });
    }

    if (this.perfilForm.get('alterarSenha')!.value === 'sim') {
      payload.alterarSenha = 'sim';
      payload.senha = this.perfilForm.get('senha')!.value;
    } else {
      payload.alterarSenha = 'nao';
      payload.senha = null;
    }

    this.salvarDados = true;
    this.voluntarioService.atualizarVoluntario(payload)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.salvarDados = false;
          this.modalMensagem.abrirSucesso('Dados atualizados com sucesso!');
          this.carregarDados();
        },
        error: (err: HttpErrorResponse) => {
          this.salvarDados = false;
          let backendMsg = 'Erro ao salvar. Tente novamente.';
          if (err.error) {
            try {
              if (err.error instanceof Blob) {
                const reader = new FileReader();
                reader.onload = () => {
                  const text = reader.result as string;
                  const parsed = JSON.parse(text);
                  this.modalMensagem.abrirErro(parsed.message || text);
                };
                reader.readAsText(err.error);
                return;
              } else {
                const errorObj = typeof err.error === 'string'
                  ? JSON.parse(err.error)
                  : err.error;
                backendMsg = errorObj.message || JSON.stringify(errorObj);
              }
            } catch {
              backendMsg = typeof err.error === 'string'
                ? err.error
                : JSON.stringify(err.error);
            }
          } else if (err.message) {
            backendMsg = err.message;
          }
          this.modalMensagem.abrirErro(backendMsg);
        }
      });
  }

  public onModalFechado() {
    if (this.modalMensagem.isSucesso) {
      this.router.navigate(['/perfil-voluntario']);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
