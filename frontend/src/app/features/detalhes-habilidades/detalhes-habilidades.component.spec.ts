import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalhesHabilidadesComponent } from './detalhes-habilidades.component';

describe('DetalhesHabilidadesComponent', () => {
  let component: DetalhesHabilidadesComponent;
  let fixture: ComponentFixture<DetalhesHabilidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalhesHabilidadesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalhesHabilidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
