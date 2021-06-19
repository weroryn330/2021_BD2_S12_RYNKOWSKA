import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuantityPassesElementComponent } from './quantity-passes-element.component';

describe('QuantityPassesElementComponent', () => {
  let component: QuantityPassesElementComponent;
  let fixture: ComponentFixture<QuantityPassesElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuantityPassesElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuantityPassesElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
