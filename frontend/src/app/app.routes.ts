import { Routes } from '@angular/router';
import { CadastroOngComponent } from './features/cadastro-ong/cadastro-ong.component';
import { CadastroVoluntarioComponent } from './features/cadastro-voluntario/cadastro-voluntario.component';
import { HomeOngComponent } from './features/home-ong/home-ong.component';
import { HomeVoluntarioComponent } from './features/home-voluntario/home-voluntario.component';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/login/login.component';
import { MeuPerfilComponent } from './features/meu-perfil/meu-perfil.component';
import { QuemSomosComponent } from './features/quem-somos/quem-somos.component';
import { RecuperarSenhaComponent } from './features/recuperar-senha/recuperar-senha.component';
import { DetalhesHabilidadesComponent } from './features/detalhes-habilidades/detalhes-habilidades.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'quem-somos', component: QuemSomosComponent },
  { path: 'home-voluntario', component: HomeVoluntarioComponent },
  { path: 'home-ong', component: HomeOngComponent },
  { path: 'cadastro-voluntario', component: CadastroVoluntarioComponent },
  { path: 'cadastro-ong', component: CadastroOngComponent },
  { path: 'meu-perfil', component: MeuPerfilComponent },
  { path: 'recuperar-senha/:token', component: RecuperarSenhaComponent },
  { path: 'detalhes-habilidades', component: DetalhesHabilidadesComponent },
];
