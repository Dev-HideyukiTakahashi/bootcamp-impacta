import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
    selector: 'app-meu-perfil',
    imports: [HeaderComponent, FooterComponent],
    standalone: true,
    templateUrl: './meu-perfil.component.html',
    styleUrl: './meu-perfil.component.scss',
})
export class MeuPerfilComponent { }