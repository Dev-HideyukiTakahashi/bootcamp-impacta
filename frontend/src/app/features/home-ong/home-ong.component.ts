import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home-ong',
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './home-ong.component.html',
  styleUrl: './home-ong.component.scss',
})
export class HomeOngComponent {}
