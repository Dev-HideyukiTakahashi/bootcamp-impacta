import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { BuscarAtividadesComponent } from './features/buscar-atividades/buscar-atividades.component';
import { CadastroAtividadeComponent } from './features/cadastro-atividade/cadastro-atividade.component';
import { CadastroOngComponent } from './features/cadastro-ong/cadastro-ong.component';
import { CadastroVoluntarioComponent } from './features/cadastro-voluntario/cadastro-voluntario.component';
import { DetalhesHabilidadesComponent } from './features/detalhes-habilidades/detalhes-habilidades.component';
import { EditarOngComponent } from './features/editar-ong/editar-ong.component';
import { EditarVoluntarioComponent } from './features/editar-voluntario/editar-voluntario.component';
import { GestaoAtividadesComponent } from './features/gestao-atividades/gestao-atividades.component';
import { HomeOngComponent } from './features/home-ong/home-ong.component';
import { HomeVoluntarioComponent } from './features/home-voluntario/home-voluntario.component';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/login/login.component';
import { PerfilOngComponent } from './features/perfil-ong/perfil-ong.component';
import { PerfilVoluntarioComponent } from './features/perfil-voluntario/perfil-voluntario.component';
import { QuemSomosComponent } from './features/quem-somos/quem-somos.component';
import { RecuperarSenhaComponent } from './features/recuperar-senha/recuperar-senha.component';
import { UserRole } from './model/enum/user-role.enum';
import { HistoricoAtividadeComponent } from './features/historico-atividade/historico-atividade.component';


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
  },

  {
    path: 'cadastro-ong',
    component: CadastroOngComponent,
  },

  {
    path: 'perfil-voluntario',
    component: PerfilVoluntarioComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Voluntario] },
  },

  {
    path: 'perfil-ong',
    component: PerfilOngComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },

  {
    path: 'dados-ong',
    component: EditarOngComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },

  {
    path: 'dados-voluntario',
    component: EditarVoluntarioComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },
  {
    path: 'atividades',
    component: GestaoAtividadesComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },
  {
    path: 'cadastro-atividade',
    component: CadastroAtividadeComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
  },
  { path: 'recuperar-senha/:token', component: RecuperarSenhaComponent },

  { path: 'detalhes-habilidades', component: DetalhesHabilidadesComponent },
  {
    path: 'atividades/editar/:id',
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
    loadComponent: () =>
      import('./features/editar-atividade/editar-atividade.component').then(
        (m) => m.EditarAtividadeComponent,
      ),
  },
  {
    path: 'gestao-voluntarios/atividade/:id',
    canActivate: [authGuard],
    data: { roles: [UserRole.Ong] },
    loadComponent: () =>
      import('./features/gestao-voluntarios/gestao-voluntarios.component').then(
        (m) => m.GestaoVoluntariosComponent,
      ),
  },
  {
    path: 'buscar-atividades',
    component: BuscarAtividadesComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Voluntario] },
  },

        {
    path: 'historico-atividade',
    component: HistoricoAtividadeComponent,
    canActivate: [authGuard],
    data: { roles: [UserRole.Voluntario] },
  },
];
