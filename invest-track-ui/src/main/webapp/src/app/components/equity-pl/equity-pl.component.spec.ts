import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquityPlComponent } from './equity-pl.component';

describe('EquityPlComponent', () => {
  let component: EquityPlComponent;
  let fixture: ComponentFixture<EquityPlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EquityPlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EquityPlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
