import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllReimbursementsComponent } from './view-all-reimbursements.component';

describe('ViewAllReimbursementsComponent', () => {
  let component: ViewAllReimbursementsComponent;
  let fixture: ComponentFixture<ViewAllReimbursementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllReimbursementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllReimbursementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
