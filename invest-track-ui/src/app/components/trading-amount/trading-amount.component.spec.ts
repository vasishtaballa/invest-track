import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TradingAmountComponent } from './trading-amount.component';

describe('TradingAmountComponent', () => {
  let component: TradingAmountComponent;
  let fixture: ComponentFixture<TradingAmountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TradingAmountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TradingAmountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
