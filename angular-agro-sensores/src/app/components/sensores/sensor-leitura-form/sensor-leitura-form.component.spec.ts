import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorLeituraFormComponent } from './sensor-leitura-form.component';

describe('SensorLeituraFormComponent', () => {
  let component: SensorLeituraFormComponent;
  let fixture: ComponentFixture<SensorLeituraFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SensorLeituraFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SensorLeituraFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
