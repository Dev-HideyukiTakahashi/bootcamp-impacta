// editar-atividade.component.ts
import {
  Component,
  OnInit,
  OnDestroy,
  AfterViewInit,
  ViewChild,
  inject,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { ModalMensagemComponent } from '../../shared/modal-mensagem/modal-mensagem.component';
import { AtividadeService } from '../../service/atividade.service';
import { TagService } from '../../service/tag.service';
import { Tag } from '../../model/tag.model';
import {
  Atividade,
  AtualizarAtividade,
  CarregarDadosAtividade,
} from '../../model/atividade.model';

@Component({
  selector: 'app-editar-atividade',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HeaderComponent,
    FooterComponent,
    ModalMensagemComponent,
  ],
  templateUrl: './editar-atividade.component.html',
  styleUrls: ['./editar-atividade.component.scss'],
})
export class EditarAtividadeComponent
  implements OnInit, OnDestroy, AfterViewInit
{
  @ViewChild(ModalMensagemComponent) modalMensagem!: ModalMensagemComponent;

  form!: FormGroup;
  tags: Tag[] = [];
  atividade!: Atividade;
  atividadeId!: number;
  formAlterado = false;
  salvarDados = false;
  selectedTagName = '';

  private destroy$ = new Subject<void>();

  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private atividadeService = inject(AtividadeService);
  private tagService = inject(TagService);

  ngOnInit(): void {
    this.atividadeId = Number(this.route.snapshot.paramMap.get('id'));

    this.form = this.fb.group({
      idTag: ['', Validators.required],
      nome: ['', Validators.required],
      periodo: ['', Validators.required],
      descricao: ['', [Validators.required, Validators.maxLength(500)]],
      cargaHorariaDiaria: ['', [Validators.required, Validators.min(1)]],
      enderecoCompleto: ['', Validators.required],
      possuiCertificacao: [null, Validators.required],
      statusAtividade: ['', Validators.required],
      dataAtividade: ['', Validators.required],
    });

    this.tagService.buscarTodos().subscribe({
      next: (tags) => (this.tags = tags),
      error: (err) => console.error('Erro ao carregar tags', err),
    });

    this.atividadeService
      .getAtividadePorId(this.atividadeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (dto) => {
          this.atividade = dto;
          this.form.patchValue({
            idTag: dto.idTag,
            nome: dto.nome,
            periodo: dto.periodo,
            cargaHorariaDiaria: dto.cargaHorariaDiaria,
            descricao: dto.descricao,
            enderecoCompleto: dto.enderecoCompleto,
            possuiCertificacao: dto.possuiCertificacao,
            statusAtividade: dto.statusAtividade,
            dataAtividade: this.formatarDataParaInput(dto.dataAtividade),
          });
          this.onTagChange(dto.idTag);
        },
        error: (err) => console.error('Erro ao carregar atividade', err),
      });

    this.form.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.verificarAlteracoes());
  }

  formatarDataParaInput(data: Date | string): string {
    const d = new Date(data);
    const ano = d.getFullYear();
    const mes = String(d.getMonth() + 1).padStart(2, '0');
    const dia = String(d.getDate()).padStart(2, '0');
    const horas = String(d.getHours()).padStart(2, '0');
    const minutos = String(d.getMinutes()).padStart(2, '0');
    return `${ano}-${mes}-${dia}T${horas}:${minutos}`;
  }

  onTagChange(eventOrId: Event | number): void {
    let tagIdSelecionada: number;

    if (typeof eventOrId === 'number') {
      tagIdSelecionada = eventOrId;
    } else {
      const selectEl = eventOrId.target as HTMLSelectElement;
      tagIdSelecionada = Number(selectEl.value);
    }

    const tagSelecionada = this.tags.find((t) => t.id === tagIdSelecionada);
    if (tagSelecionada) {
      this.form.get('nome')!.setValue(tagSelecionada.nome);
      this.selectedTagName = tagSelecionada.nome;
    }
  }

  verificarAlteracoes() {
    if (!this.atividade) return;
    const f = this.form.value;
    this.formAlterado =
      Number(f.idTag) !== Number(this.atividade.idTag) ||
      f.periodo !== this.atividade.periodo ||
      f.cargaHorariaDiaria !== this.atividade.cargaHorariaDiaria ||
      f.descricao !== this.atividade.descricao ||
      f.enderecoCompleto !== this.atividade.enderecoCompleto ||
      f.possuiCertificacao !== this.atividade.possuiCertificacao ||
      f.nome !== this.atividade.nome ||
      f.statusAtividade !== this.atividade.statusAtividade ||
      this.formatarDataParaInput(this.atividade.dataAtividade) !==
        f.dataAtividade;
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (!this.formAlterado) {
      this.modalMensagem.abrirErro('Nenhuma alteração detectada.');
      return;
    }

    const valorForm = this.form.getRawValue(); // inclui campos disabled

    const payload = {
      nome: valorForm.nome,
      periodo: valorForm.periodo,
      cargaHorariaDiaria: valorForm.cargaHorariaDiaria,
      enderecoCompleto: valorForm.enderecoCompleto,
      possuiCertificacao: valorForm.possuiCertificacao,
      descricao: this.atividade.descricao, // Adiciona a descrição existente ou string vazia
      statusAtividade: valorForm.statusAtividade,
      dataAtividade: new Date(valorForm.dataAtividade),
      idTag: Number(valorForm.idTag),
    };

    this.salvarDados = true;
    this.atividadeService;
    this.atividadeService
      .atualizarAtividade(this.atividadeId, payload as AtualizarAtividade)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.salvarDados = false;
          this.modalMensagem.abrirSucesso('Atividade atualizada com sucesso!');
        },
        error: (err) => {
          this.salvarDados = false;
          this.modalMensagem.abrirErro('Erro ao atualizar atividade.');
          console.error(err);
        },
      });
  }

  onModalFechado() {
    if (this.modalMensagem.isSucesso) {
      this.router.navigate(['/atividades']);
    }
  }

  ngAfterViewInit(): void {
    this.modalMensagem.fechado
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.onModalFechado());
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
