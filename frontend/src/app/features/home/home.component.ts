// home.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-home',
  imports: [HeaderComponent, FooterComponent, RouterModule],
  standalone: true, // 👈 ESSENCIAL
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {}
