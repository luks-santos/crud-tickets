import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketRegisterDialogComponent } from './ticket-register-dialog.component';

describe('TicketRegisterDialogComponent', () => {
  let component: TicketRegisterDialogComponent;
  let fixture: ComponentFixture<TicketRegisterDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TicketRegisterDialogComponent]
    });
    fixture = TestBed.createComponent(TicketRegisterDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
