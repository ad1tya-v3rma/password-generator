import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlitchTextComponent } from './glitch-text.component';

describe('GlitchTextComponent', () => {
  let component: GlitchTextComponent;
  let fixture: ComponentFixture<GlitchTextComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GlitchTextComponent]
    });
    fixture = TestBed.createComponent(GlitchTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
