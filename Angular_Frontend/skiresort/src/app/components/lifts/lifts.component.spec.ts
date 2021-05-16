import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiftsComponent } from './lifts.component';

describe('LiftsComponent', () => {
  let component: LiftsComponent;
  let fixture: ComponentFixture<LiftsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiftsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiftsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
