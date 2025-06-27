import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PerfilVoluntario } from '../../model/VoluntarioModel';
import { VoluntarioService } from '../../service/voluntario.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-perfil-voluntario',
  standalone: true,
  imports: [RouterModule, CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './perfil-voluntario.component.html',
  styleUrls: ['./perfil-voluntario.component.scss'],
})
export class PerfilVoluntarioComponent implements OnInit, OnDestroy {
  perfil?: PerfilVoluntario;
  private destroy$ = new Subject<void>();

  constructor(private voluntarioService: VoluntarioService) {}
  ngOnInit(): void {
    this.voluntarioService
      .getMeuPerfil()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (dto: PerfilVoluntario) => (this.perfil = dto),
        error: (err: any) => console.error('Erro ao carregar perfil:', err),
      });
  }

  get tagIdsString(): string {
    return this.perfil!.tags.map((tag) => tag.id).join(',');
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
