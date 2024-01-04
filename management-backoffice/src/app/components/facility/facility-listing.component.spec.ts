import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityListingComponent } from './facility-listing.component';

describe('ListingComponent', () => {
  let component: FacilityListingComponent;
  let fixture: ComponentFixture<FacilityListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityListingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
