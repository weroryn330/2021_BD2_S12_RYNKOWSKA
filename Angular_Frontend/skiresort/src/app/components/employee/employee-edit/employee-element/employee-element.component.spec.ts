import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeElementComponent } from './employee-element.component';

describe('EmployeeElementComponent', () => {
  let component: EmployeeElementComponent;
  let fixture: ComponentFixture<EmployeeElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
