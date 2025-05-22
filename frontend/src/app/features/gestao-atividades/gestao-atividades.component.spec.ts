import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestaoAtividadesComponent } from './gestao-atividades.component';

describe('GestaoAtividadeComponent', () => {
  let component: GestaoAtividadesComponent;
  let fixture: ComponentFixture<GestaoAtividadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestaoAtividadesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestaoAtividadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
