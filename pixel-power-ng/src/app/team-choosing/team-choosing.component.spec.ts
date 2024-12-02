import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamChoosingComponent } from './team-choosing.component';

describe('TeamChoosingComponent', () => {
  let component: TeamChoosingComponent;
  let fixture: ComponentFixture<TeamChoosingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamChoosingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamChoosingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
