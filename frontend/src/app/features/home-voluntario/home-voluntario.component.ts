import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home-voluntario',
  imports: [HeaderComponent, FooterComponent, RouterModule],
  templateUrl: './home-voluntario.component.html',
  styleUrl: './home-voluntario.component.scss',
})
export class HomeVoluntarioComponent {
  router = inject(Router);

  navigate() {
    this.router.navigate(['/perfil-voluntario']);
  }
}
