import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementPasswordFormComponent } from './element-password-form.component';

describe('ElementPasswordFormComponent', () => {
  let component: ElementPasswordFormComponent;
  let fixture: ComponentFixture<ElementPasswordFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementPasswordFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementPasswordFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
