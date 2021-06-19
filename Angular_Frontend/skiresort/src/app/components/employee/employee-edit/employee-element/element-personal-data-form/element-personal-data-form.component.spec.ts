import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementPersonalDataFormComponent } from './element-personal-data-form.component';

describe('ElementPersonalDataFormComponent', () => {
  let component: ElementPersonalDataFormComponent;
  let fixture: ComponentFixture<ElementPersonalDataFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementPersonalDataFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementPersonalDataFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
