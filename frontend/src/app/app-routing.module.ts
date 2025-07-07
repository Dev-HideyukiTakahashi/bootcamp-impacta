import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/login/login.component';
import { CadastroVoluntarioComponent } from './features/cadastro-voluntario/cadastro-voluntario.component';
import { CadastroOngComponent } from './features/cadastro-ong/cadastro-ong.component';
import { EditarVoluntarioComponent } from './features/editar-voluntario/editar-voluntario.component';
import { GestaoAtividadesComponent } from './features/gestao-atividades/gestao-atividades.component';
import { GestaoVoluntariosComponent } from './features/gestao-voluntarios/gestao-voluntarios.component';

const routes: Routes = [
  // rota padrão redireciona para a lista de atividades
  { path: '', redirectTo: 'atividades', pathMatch: 'full' },

  // páginas principais
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },

  // cadastros
  { path: 'cadastro-voluntario', component: CadastroVoluntarioComponent },
  { path: 'cadastro-ong', component: CadastroOngComponent },
  { path: 'voluntario/editar', component: EditarVoluntarioComponent },

  // gestão de atividades
  { path: 'atividades', component: GestaoAtividadesComponent },

  // gestão de voluntários com parâmetro de atividade
  { path: 'gestao-voluntarios/atividade/:atividadeId', component: GestaoVoluntariosComponent },



  // fallback para rotas desconhecidas
  { path: '**', redirectTo: 'atividades' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
