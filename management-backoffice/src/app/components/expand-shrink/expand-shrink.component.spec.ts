import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandShrinkComponent } from './expand-shrink.component';

describe('ExpandShrinkComponent', () => {
  let component: ExpandShrinkComponent;
  let fixture: ComponentFixture<ExpandShrinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpandShrinkComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpandShrinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
