import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTodolistComponent } from './form-todolist.component';

describe('NewTodolistComponent', () => {
  let component: FormTodolistComponent;
  let fixture: ComponentFixture<FormTodolistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormTodolistComponent]
    });
    fixture = TestBed.createComponent(FormTodolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
