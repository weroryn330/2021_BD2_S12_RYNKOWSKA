import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserEditInfoComponent } from './user-edit-info.component';

describe('UserEditInfoComponent', () => {
  let component: UserEditInfoComponent;
  let fixture: ComponentFixture<UserEditInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserEditInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserEditInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
