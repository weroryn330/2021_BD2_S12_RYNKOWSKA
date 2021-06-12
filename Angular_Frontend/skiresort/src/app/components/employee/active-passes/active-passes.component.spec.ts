import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivePassesComponent } from './active-passes.component';

describe('ActivePassesComponent', () => {
  let component: ActivePassesComponent;
  let fixture: ComponentFixture<ActivePassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivePassesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivePassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
