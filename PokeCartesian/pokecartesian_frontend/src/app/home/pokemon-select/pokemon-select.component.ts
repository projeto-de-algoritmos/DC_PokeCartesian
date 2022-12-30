import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from '../services/pokemon.service';
import {Pokemons} from '../interfaces/pokemons';
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {ErrorComponent} from "../pokemon-not-found/error.component";
import { PokemonFoundComponent } from '../pokemon-found/pokemon-found.component';
import {Observable} from "rxjs";


@Component({
  selector: 'app-pokemon-select',
  templateUrl: './pokemon-select.component.html',
  styleUrls: ['./pokemon-select.component.css']
})
export class PokemonSelectComponent implements OnInit {
  matrix: Observable<Pokemons[]> | undefined;

  pokemonForm = this.formBuilder.group({});

  constructor(
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {
    this.loadMatrix()
  }

  loadMatrix(): void {
    this.matrix = this.pokemonService.cartesianPlane();
  }

  openDialogError(): void {
    const dialogRef = this.dialog.open(ErrorComponent, {
      width: '500px',
      height: '500px',
    });
  }

  openDialog(response: any): void {
    const dialogRef = this.dialog.open(PokemonFoundComponent, {
      width: '500px',
      height: '500px',
      data: response,
    });
  }

  find() {
    this.pokemonService.closestPokemon().subscribe((response: any) => {
      console.log(response)
      this.openDialog(response);
      }, (error: any) => {
      console.log(error)
      this.openDialogError();
      }
    );
  }
}
