import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleScheduleElementComponent } from './single-schedule-element.component';

describe('SingleScheduleElementComponent', () => {
  let component: SingleScheduleElementComponent;
  let fixture: ComponentFixture<SingleScheduleElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleScheduleElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleScheduleElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
