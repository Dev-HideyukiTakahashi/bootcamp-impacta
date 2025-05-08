import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-menu',
  imports: [CommonModule, RouterModule],
  standalone: true,
  templateUrl: './header-menu.component.html',
  styleUrl: './header-menu.component.scss',
})
export class HeaderMenuComponent {
  @Input()
  logado: 'voluntario' | 'ong' | 'deslogado' = 'deslogado';

  @Output()
  buttonLogout: EventEmitter<void> = new EventEmitter<void>();

  logout() {
    this.buttonLogout.emit();
  }
}
