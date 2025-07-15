// modal-avaliar-voluntario.component.ts
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { VoluntarioHistoricoResponseDTO } from '../gestao-voluntarios.component';
import { AvaliacaoResponse, AvaliacaoDTO } from '../../../model/avaliacao.model';
import { CommonModule } from '@angular/common';
import { FormsModule }  from '@angular/forms';
@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  selector: 'app-modal-avaliar-voluntario',
  templateUrl: './modal-avaliar-voluntario.component.html',
  styleUrls: ['./modal-avaliar-voluntario.component.scss']
})
export class ModalAvaliarVoluntarioComponent implements OnChanges {
  @Input() voluntario!: VoluntarioHistoricoResponseDTO;
  @Input() readonlyMode = false;                   // ← flag de modo
  @Input() initialAvaliacao?: AvaliacaoResponse;   // ← dados para resumo

  @Output() onSubmit = new EventEmitter<AvaliacaoDTO>();
  @Output() onClose  = new EventEmitter<void>();

  // campos do form
  participou!: boolean;
  horas!: number;
  comentario = '';
  nota = 0;

  ngOnChanges(changes: SimpleChanges) {
    // quando o parent setar initialAvaliacao para visão de resumo:
    if (this.readonlyMode && this.initialAvaliacao) {
      // pré‑carrega campos somente para exibição
      this.comentario = this.initialAvaliacao.feedback;
      this.nota      = this.initialAvaliacao.estrelas;
      // se você tiver cargaHoraria no response, faça parse aqui
    }
  }

  submit() {
    if (this.readonlyMode) {
      // não fazer nada (ou apenas fechar)
      this.onClose.emit();
      return;
    }
    // modo edição: dispara evento normal
    this.onSubmit.emit({
      voluntarioId: this.voluntario.id,
      participou:   this.participou,
      horas:        this.horas,
      comentario:   this.comentario,
      nota:         this.nota
    });
  }

  close() {
    this.onClose.emit();
  }
}
