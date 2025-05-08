import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-quem-somos',
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './quem-somos.component.html',
  styleUrl: './quem-somos.component.scss',
})
export class QuemSomosComponent {}
