import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarOngComponent } from './editar-ong.component';

describe('EditarOngComponent', () => {
  let component: EditarOngComponent;
  let fixture: ComponentFixture<EditarOngComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarOngComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarOngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
