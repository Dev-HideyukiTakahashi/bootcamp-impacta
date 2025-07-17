import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-quem-somos',
  imports: [HeaderComponent, FooterComponent, RouterModule],
  templateUrl: './quem-somos.component.html',
  styleUrl: './quem-somos.component.scss',
})
export class QuemSomosComponent {}
