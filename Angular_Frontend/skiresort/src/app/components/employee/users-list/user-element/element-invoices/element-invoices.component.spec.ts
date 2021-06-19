import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementInvoicesComponent } from './element-invoices.component';

describe('ElementInvoicesComponent', () => {
  let component: ElementInvoicesComponent;
  let fixture: ComponentFixture<ElementInvoicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementInvoicesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementInvoicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
