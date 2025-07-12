import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home-voluntario',
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './home-voluntario.component.html',
  styleUrl: './home-voluntario.component.scss',
})
export class HomeVoluntarioComponent {}
