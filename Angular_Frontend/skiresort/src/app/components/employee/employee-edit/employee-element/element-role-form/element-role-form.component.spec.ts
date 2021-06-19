import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementRoleFormComponent } from './element-role-form.component';

describe('ElementRoleFormComponent', () => {
  let component: ElementRoleFormComponent;
  let fixture: ComponentFixture<ElementRoleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElementRoleFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElementRoleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
