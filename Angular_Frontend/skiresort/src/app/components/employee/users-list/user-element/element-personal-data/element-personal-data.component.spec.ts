import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementPersonalDataComponent } from './element-personal-data.component';

describe('ElementPersonalDataComponent', () => {
  let component: ElementPersonalDataComponent;
  let fixture: ComponentFixture<ElementPersonalDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementPersonalDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementPersonalDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
