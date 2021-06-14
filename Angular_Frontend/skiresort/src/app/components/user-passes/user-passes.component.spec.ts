import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPassesComponent } from './user-passes.component';

describe('UserPassesComponent', () => {
  let component: UserPassesComponent;
  let fixture: ComponentFixture<UserPassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPassesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
