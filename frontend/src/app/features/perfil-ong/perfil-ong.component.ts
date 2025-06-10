import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { OngService } from '../../service/ong.service';
import { PerfilOng } from '../../model/OngModel';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { NgxMaskPipe } from 'ngx-mask';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-perfil-ong.component',
  standalone: true,
  imports: [NgxMaskPipe, CommonModule, HeaderComponent, FooterComponent, RouterModule],
  templateUrl: './perfil-ong.component.html',
  styleUrls: ['./perfil-ong.component.scss']
})
export class PerfilOngComponent implements OnInit, OnDestroy {
  perfil?: PerfilOng;
  private destroy$ = new Subject<void>();

  constructor(private perfilSvc: OngService) {}

  ngOnInit(): void {
    this.perfilSvc.getMeuPerfil()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (dto: PerfilOng) => this.perfil = dto,
        error: (err: any) => console.error('Erro ao carregar perfil da ONG:', err)
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
