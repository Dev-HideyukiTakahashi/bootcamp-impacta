import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAvaliarVoluntarioComponent } from './modal-avaliar-voluntario.component';

describe('ModalAvaliarVoluntarioComponent', () => {
  let component: ModalAvaliarVoluntarioComponent;
  let fixture: ComponentFixture<ModalAvaliarVoluntarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalAvaliarVoluntarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAvaliarVoluntarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
