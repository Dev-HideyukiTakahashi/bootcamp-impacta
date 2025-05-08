import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../service/authService.service';
import { HeaderMenuComponent } from '../header-menu/header-menu.component';

@Component({
  selector: 'app-header',
  imports: [CommonModule, HeaderMenuComponent],
  standalone: true,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  private authService = inject(AuthService);
  private router = inject(Router);

  ongLogado = false;
  voluntarioLogado = false;

  // o método construtor é chamado antes de iniciar do componente e portanto ainda não existe localStorage
  // o ngOnInit é chamado depois que o componente é inicializado para evitar erro de undefined
  ngOnInit() {
    if (typeof window !== 'undefined') {
      this.ongLogado = this.authService.isOng();
      this.voluntarioLogado = this.authService.isVoluntario();
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  logo() {
    if (this.ongLogado || this.voluntarioLogado)
      this.ongLogado
        ? this.router.navigate(['/home-ong'])
        : this.router.navigate(['/home-voluntario']);
  }
}
