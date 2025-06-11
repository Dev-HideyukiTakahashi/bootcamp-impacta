import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/login/login.component';
import { CadastroVoluntarioComponent } from './features/cadastro-voluntario/cadastro-voluntario.component';
import { CadastroOngComponent } from './features/cadastro-ong/cadastro-ong.component';
import { EditarVoluntarioComponent } from './features/editar-voluntario/editar-voluntario.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cadastro-voluntario', component: CadastroVoluntarioComponent },
  { path: 'cadastro-ong', component: CadastroOngComponent },
  { path: 'voluntario/editar', component: EditarVoluntarioComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
