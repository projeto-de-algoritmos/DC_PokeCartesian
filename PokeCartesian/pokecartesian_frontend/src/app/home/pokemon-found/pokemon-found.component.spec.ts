import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonFoundComponent } from './pokemon-found.component';

describe('PokemonFoundComponent', () => {
  let component: PokemonFoundComponent;
  let fixture: ComponentFixture<PokemonFoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonFoundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PokemonFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
