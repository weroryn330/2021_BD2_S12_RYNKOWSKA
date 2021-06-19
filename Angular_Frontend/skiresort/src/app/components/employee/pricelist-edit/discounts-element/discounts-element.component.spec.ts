import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscountsElementComponent } from './discounts-element.component';

describe('DiscountsElementComponent', () => {
  let component: DiscountsElementComponent;
  let fixture: ComponentFixture<DiscountsElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiscountsElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscountsElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
