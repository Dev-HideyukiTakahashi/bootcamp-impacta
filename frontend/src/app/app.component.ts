import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ModalMensagemComponent } from './shared/modal-mensagem/modal-mensagem.component';


@Component({
  selector: 'app-root',
  standalone: true, // 👈 isso é importante se estiver usando standalone aqui também
  imports: [RouterOutlet, ModalMensagemComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {}
