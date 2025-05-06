import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service/authService.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-recuperar-senha',
  imports: [ReactiveFormsModule, HeaderComponent, FooterComponent, CommonModule],
  templateUrl: './recuperar-senha.component.html',
  styleUrl: './recuperar-senha.component.scss',
})
export class RecuperarSenhaComponent implements OnInit {
  token!: string;
  form: FormGroup;
  errorMessage: string | null = null;

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private authService = inject(AuthService);

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token')!;
  }

  constructor(private fb: FormBuilder) {
    this.form = this.fb.nonNullable.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    // checando se as senhas foram validadas para continuar
    if (!this.isSenhaValida()) return;

    const { password } = this.form.value;

    this.authService.resetPassword(this.token, password).subscribe({
      next: () => {
        alert('Senha alterada com sucesso!'), this.router.navigate(['/login']);
      },
      error: (err) => (this.errorMessage = 'Token de validação expirado'),
    });
  }

  private isSenhaValida(): boolean {
    const { password, confirmPassword } = this.form.value;
    const passwordControl = this.form.get('password');

    // Limpa mensagens de erro anteriores
    this.errorMessage = '';

    // Validação de campos obrigatórios
    if (!password || !confirmPassword) {
      this.errorMessage = 'Todos os campos são obrigatórios';
      return false;
    }

    // Validação de tamanho mínimo
    if (passwordControl?.errors?.['minlength']) {
      this.errorMessage = 'Senha no mínimo 6 caracteres';
      return false;
    }

    // Validação de senhas coincidentes
    if (password !== confirmPassword) {
      this.errorMessage = 'As senhas não coincidem';
      return false;
    }

    // Se todas as validações passarem
    return true;
  }
}
