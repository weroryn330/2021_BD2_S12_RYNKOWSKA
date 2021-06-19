import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementEmailFormComponent } from './element-email-form.component';

describe('ElementEmailFormComponent', () => {
  let component: ElementEmailFormComponent;
  let fixture: ComponentFixture<ElementEmailFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementEmailFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementEmailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
