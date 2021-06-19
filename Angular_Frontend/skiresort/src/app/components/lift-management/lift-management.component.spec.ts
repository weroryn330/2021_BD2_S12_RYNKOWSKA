import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiftManagementComponent } from './lift-management.component';

describe('LiftManagementComponent', () => {
  let component: LiftManagementComponent;
  let fixture: ComponentFixture<LiftManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiftManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiftManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
