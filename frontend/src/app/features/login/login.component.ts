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

  loginModel: LoginModel = {} as LoginModel;

  private authService = inject(AuthService);

  submitLogin() {
    this.authService.login(this.loginModel.username, this.loginModel.password).subscribe({
      next: (response: any) => {
        const accessToken = response.access_token;
        const decodedToken: any = jwtDecode(accessToken);
        const role = decodedToken.authorities[0];

        localStorage.setItem('access_token', response.access_token);
        localStorage.setItem('tipo_usuario', role);

        console.log('Tipo de usuário:', role);

        //const role = this.authService.getUserRole();

        // redirecionar para a tela de acordo com o tipo de usuário
        role === 'ROLE_ONG'
          ? this.router.navigate(['/home-ong'])
          : this.router.navigate(['/home-voluntario']);
      },
      error: (err) => {
        console.error('Erro ao fazer login:', err);
      },
    });
  }
}
