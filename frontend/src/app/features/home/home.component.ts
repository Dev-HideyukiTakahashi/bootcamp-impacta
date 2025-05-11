// home.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
<<<<<<< HEAD
@Component({
  selector: 'app-home',
  imports: [HeaderComponent, FooterComponent],
=======
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-home',
  imports: [HeaderComponent, FooterComponent, RouterModule],
>>>>>>> origin/feature/cadastro
  standalone: true, // 👈 ESSENCIAL
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {}
