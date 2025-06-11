import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface ModalMensagemData {
  tipo: 'sucesso' | 'erro';
  mensagem: string;
}

@Injectable({
  providedIn: 'root'
})
export class ModalMensagemService {
  private _modalState = new BehaviorSubject<ModalMensagemData | null>(null);

  // Observable para o componente modal escutar
  modalState$ = this._modalState.asObservable();

  abrirSucesso(mensagem: string) {
    this._modalState.next({ tipo: 'sucesso', mensagem });
  }

  abrirErro(mensagem: string) {
    this._modalState.next({ tipo: 'erro', mensagem });
  }

  fecharModal() {
    this._modalState.next(null);
  }
}
