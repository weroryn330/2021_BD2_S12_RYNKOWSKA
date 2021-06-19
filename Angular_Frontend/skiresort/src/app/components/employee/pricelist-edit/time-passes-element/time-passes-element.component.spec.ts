import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimePassesElementComponent } from './time-passes-element.component';

describe('TimePassesElementComponent', () => {
  let component: TimePassesElementComponent;
  let fixture: ComponentFixture<TimePassesElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimePassesElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimePassesElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
