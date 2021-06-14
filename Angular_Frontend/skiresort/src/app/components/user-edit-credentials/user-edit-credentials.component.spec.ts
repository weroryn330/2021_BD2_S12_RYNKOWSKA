import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserEditCredentialsComponent } from './user-edit-credentials.component';

describe('UserEditCredentialsComponent', () => {
  let component: UserEditCredentialsComponent;
  let fixture: ComponentFixture<UserEditCredentialsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserEditCredentialsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserEditCredentialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
