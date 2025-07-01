import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarAtividadesComponent } from './buscar-atividades.component';

describe('BuscarAtividadesComponent', () => {
  let component: BuscarAtividadesComponent;
  let fixture: ComponentFixture<BuscarAtividadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuscarAtividadesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarAtividadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
