import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementSingleQuantityPassComponent } from './element-single-quantity-pass.component';

describe('ElementSingleQuantityPassComponent', () => {
  let component: ElementSingleQuantityPassComponent;
  let fixture: ComponentFixture<ElementSingleQuantityPassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementSingleQuantityPassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementSingleQuantityPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
