import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInvoicesComponent } from './user-invoices.component';

describe('UserInvoicesComponent', () => {
  let component: UserInvoicesComponent;
  let fixture: ComponentFixture<UserInvoicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserInvoicesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInvoicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
