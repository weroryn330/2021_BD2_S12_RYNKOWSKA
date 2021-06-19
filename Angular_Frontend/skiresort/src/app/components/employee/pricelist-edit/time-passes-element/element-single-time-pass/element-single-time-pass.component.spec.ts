import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementSingleTimePassComponent } from './element-single-time-pass.component';

describe('ElementSingleTimePassComponent', () => {
  let component: ElementSingleTimePassComponent;
  let fixture: ComponentFixture<ElementSingleTimePassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementSingleTimePassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementSingleTimePassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
