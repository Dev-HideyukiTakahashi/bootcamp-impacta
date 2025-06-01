import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { LoginModel } from '../../model/LoginModel';
import { AuthService } from '../../service/authService.service';
import { HeaderComponent } from '../../shared/components/header/header.component';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, HeaderComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  private router = inject(Router);
  private authService = inject(AuthService);

  errorMessage: string | null = null;
  loginModel: LoginModel = {} as LoginModel;

  submitLogin() {
    this.errorMessage = this.validateForm();
    if (this.errorMessage) return;

    this.authService.login(this.loginModel.username, this.loginModel.password).subscribe({
      next: (response: any) => {
        const accessToken = response.access_token;
        const decodedToken: any = jwtDecode(accessToken);
        const role = decodedToken.authorities[0];

        localStorage.setItem('access_token', response.access_token);
        localStorage.setItem('tipo_usuario', role);

        console.log('Tipo de usuário:', role);
        console.log('Resposta do login:', response);
        //const role = this.authService.getUserRole();

        // redirecionar para a tela de acordo com o tipo de usuário
        role === 'ROLE_ONG'
          ? this.router.navigate(['/home-ong'])
          : this.router.navigate(['/home-voluntario']);
      },
      error: () => {
        this.errorMessage = 'Usuário ou senha inválido';
      },
    });
  }

  private validateForm(): string | null {
    if (!this.loginModel.username) return 'O email é obrigatório';

    if (!this.loginModel.password) return 'A senha é obrigatória';

    if (!this.loginModel.username.includes('@')) return 'Por favor, insira um email válido';

    return null;
  }

  // Métodos para o modal
  showForgotPasswordModal = false;
  recoveryEmail = '';
  forgotPasswordError: string | null = null;

  openForgotPasswordModal(): void {
    this.showForgotPasswordModal = true;
    this.forgotPasswordError = null;
    this.recoveryEmail = '';
  }

  closeModal(): void {
    this.showForgotPasswordModal = false;
  }

  submitForgotPassword(): void {
    this.forgotPasswordError = '';
    if (!this.recoveryEmail || !this.recoveryEmail.includes('@')) {
      this.forgotPasswordError = 'Por favor, insira um email válido';
      return;
    }

    this.authService.recuperarSenha(this.recoveryEmail).subscribe({
      next: () => {
        alert('Email de recuperação enviado com sucesso!');
        this.closeModal();
      },
      error: () => {
        this.forgotPasswordError = 'Email não cadastrado';
      },
    });
  }
}
