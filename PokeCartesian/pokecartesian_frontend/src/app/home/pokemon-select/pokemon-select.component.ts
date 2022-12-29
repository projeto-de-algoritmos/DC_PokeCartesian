import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from '../services/pokemon.service';
import {Pokemons} from '../interfaces/pokemons';
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {PokemonNotFoundComponent} from "../pokemon-not-found/pokemon-not-found.component";
import { PokemonFoundComponent } from '../pokemon-found/pokemon-found.component';
import {Observable} from "rxjs";


@Component({
  selector: 'app-pokemon-select',
  templateUrl: './pokemon-select.component.html',
  styleUrls: ['./pokemon-select.component.css']
})
export class PokemonSelectComponent implements OnInit {

  matrix: Observable<Pokemons[]> | undefined;
  optionsPokemon: any[] = [];
  optionsAreas: any[] = [];
  imageAlt: any;
  imageSourceSelf: any;
  imageArea: any;
  pokemon: any = null;
  area: any = null;

  pokemonForm = this.formBuilder.group({
    pokemon: '',
    area: '',
  });

  constructor(
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {
    this.matrix = this.pokemonService.cartesianPlane();
  }

  openDialogError(): void {
    const dialogRef = this.dialog.open(PokemonNotFoundComponent, {
      width: '500px',
      height: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDialog(response: any): void {
    const dialogRef = this.dialog.open(PokemonFoundComponent, {
      width: '500px',
      height: '500px',
      data: response,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  extractPokemon(pokemon: Pokemons) {
    return {
      id: pokemon.id,
      name: pokemon.name,
      url: pokemon.url
    };
  }

  loadPokemonSelf($event: MatSelectChange) {
    if($event.value !== null){
      this.imageSourceSelf = $event.value.url
      this.pokemon = $event.value.id
    } else {
      this.pokemon = $event.value
    }
  }

  loadArea($event: MatSelectChange) {
    if($event.value !== null){
      this.imageArea = $event.value.url
      this.area = $event.value.id
    } else {
      this.area = $event.value
    }
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
