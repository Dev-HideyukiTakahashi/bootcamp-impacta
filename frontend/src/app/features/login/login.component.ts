import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../service/authService.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  private authService = inject(AuthService);

  submitLogin() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response: any) => {
        console.log(`Usuário logado com sucesso!`); // TODO remover quando implementar tela pós login
        localStorage.setItem('access_token', response.access_token);
        localStorage.setItem('refresh_token', response.refresh_token);
      },
      error: (err) => {
        console.error('Erro ao fazer login:', err);
      },
    });
  }
}
