import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { VoluntarioService } from '../../service/voluntario.service';
import { PerfilVoluntario } from '../../model/VoluntarioModel';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-perfil-voluntario',
  standalone: true,
  imports: [ RouterModule, CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './perfil-voluntario.component.html',
  styleUrls: ['./perfil-voluntario.component.scss']
})
export class PerfilVoluntarioComponent implements OnInit, OnDestroy {
  perfil?: PerfilVoluntario;
  private destroy$ = new Subject<void>();

  constructor(private voluntarioService: VoluntarioService) {}
  ngOnInit(): void {
    this.voluntarioService.getMeuPerfil()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (dto: PerfilVoluntario) => this.perfil = dto,
        error: (err: any) => console.error('Erro ao carregar perfil:', err)
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
