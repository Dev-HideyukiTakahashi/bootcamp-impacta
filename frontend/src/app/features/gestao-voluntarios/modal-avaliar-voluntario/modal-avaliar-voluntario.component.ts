import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListaInscritos } from './../../../service/gestaoVoluntario.service';
// modal-avaliar-voluntario.component.ts
import { Component, EventEmitter, Input, Output } from '@angular/core';

export interface AvaliacaoDTO {
  voluntarioId: number;
  participou: boolean;
  horas: number;
  comentario: string;
  nota: number;
}

@Component({
  selector: 'app-modal-avaliar-voluntario',
  templateUrl: './modal-avaliar-voluntario.component.html',
  styleUrls: ['./modal-avaliar-voluntario.component.scss'],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule  ],
})
export class ModalAvaliarVoluntarioComponent {
  @Input() voluntario!: ListaInscritos;
  @Output() onSubmit = new EventEmitter<AvaliacaoDTO>();
  @Output() onClose = new EventEmitter<void>();

  participou: boolean | null = null;
  horas: number | null = null;
  comentario = '';
  nota = 0;

  selectHora(e: Event) {
    const input = e.target as HTMLInputElement;
    this.horas = input.valueAsNumber;
  }

  setNota(i: number) {
    this.nota = i;
  }

  submit() {
    window.alert('SEM BACKEND');
  }

  close() {
    this.onClose.emit();
  }
}
