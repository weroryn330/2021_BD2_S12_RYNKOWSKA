import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementSingleDiscountComponent } from './element-single-discount.component';

describe('ElementSingleDiscountComponent', () => {
  let component: ElementSingleDiscountComponent;
  let fixture: ComponentFixture<ElementSingleDiscountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementSingleDiscountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementSingleDiscountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
