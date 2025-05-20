import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { CadastroVoluntarioComponent } from './features/cadastro-voluntario/cadastro-voluntario.component';
import { HomeOngComponent } from './features/home-ong/home-ong.component';
import { HomeVoluntarioComponent } from './features/home-voluntario/home-voluntario.component';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/login/login.component';
import { MeuPerfilComponent } from './features/meu-perfil/meu-perfil.component';
import { QuemSomosComponent } from './features/quem-somos/quem-somos.component';
import { RecuperarSenhaComponent } from './features/recuperar-senha/recuperar-senha.component';
import { UserRole } from './model/enum/user-role.enum';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'quem-somos', component: QuemSomosComponent },
  {
    path: 'home-voluntario',
    component: HomeVoluntarioComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Voluntario] },
  },
  {
    path: 'home-ong',
    component: HomeOngComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },
  {
    path: 'cadastro-voluntario',
    component: CadastroVoluntarioComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Voluntario] },
  },
  { path: 'meu-perfil', component: MeuPerfilComponent },
  { path: 'recuperar-senha/:token', component: RecuperarSenhaComponent },
];
