// home.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true, // 👈 ESSENCIAL
  templateUrl: './home.component.html'
})
export class HomeComponent {
  constructor(private router: Router) {}

  Login(): void {
    this.router.navigate(['/login']);
  }
}
