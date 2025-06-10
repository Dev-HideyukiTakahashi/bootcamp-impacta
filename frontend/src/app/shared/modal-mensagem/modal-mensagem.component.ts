import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal-mensagem',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal-mensagem.component.html',
  styleUrls: ['./modal-mensagem.component.scss']
})
export class ModalMensagemComponent {
  @Output() fechado = new EventEmitter<void>();

  msg: string = '';
  erro: string = '';
  isAberto: boolean = false;
  private tipo: 'sucesso' | 'erro' = 'erro';

  abrirSucesso(mensagem: string) {
    this.tipo = 'sucesso';
    this.msg = mensagem;
    this.erro = '';
    this.isAberto = true;

    setTimeout(() => this.fecharModal(), 2000);
  }

  abrirErro(mensagem: string) {
    this.tipo = 'erro';
    this.erro = mensagem;
    this.msg = '';
    this.isAberto = true;
  }

  fecharModal() {
    this.isAberto = false;
    this.msg = '';
    this.erro = '';
    this.fechado.emit();
  }

  get isSucesso(): boolean {
    return this.tipo === 'sucesso';
  }
}
