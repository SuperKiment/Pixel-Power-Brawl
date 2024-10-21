import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KimentComponent } from './kiment.component';

describe('KimentComponent', () => {
  let component: KimentComponent;
  let fixture: ComponentFixture<KimentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KimentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KimentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
