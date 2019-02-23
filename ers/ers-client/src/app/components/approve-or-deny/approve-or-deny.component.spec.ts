import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveOrDenyComponent } from './approve-or-deny.component';

describe('ApproveOrDenyComponent', () => {
  let component: ApproveOrDenyComponent;
  let fixture: ComponentFixture<ApproveOrDenyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveOrDenyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveOrDenyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
