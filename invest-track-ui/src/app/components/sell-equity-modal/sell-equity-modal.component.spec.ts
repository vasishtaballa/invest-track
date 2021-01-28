import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellEquityModalComponent } from './sell-equity-modal.component';

describe('SellEquityModalComponent', () => {
  let component: SellEquityModalComponent;
  let fixture: ComponentFixture<SellEquityModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellEquityModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellEquityModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
