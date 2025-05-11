import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
<<<<<<< HEAD

@Component({
  selector: 'app-quem-somos',
  imports: [HeaderComponent],
=======
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-quem-somos',
  imports: [HeaderComponent, FooterComponent],
>>>>>>> origin/feature/cadastro
  templateUrl: './quem-somos.component.html',
  styleUrl: './quem-somos.component.scss',
})
export class QuemSomosComponent {}
