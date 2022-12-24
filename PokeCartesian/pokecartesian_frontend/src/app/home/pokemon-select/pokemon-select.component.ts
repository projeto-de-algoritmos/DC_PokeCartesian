import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from '../services/pokemon.service';
import {Pokemon} from '../interfaces/pokemons';
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {AreaService} from "../services/area.service";
import {Area} from "../interfaces/areas";
import {PokemonNotFoundComponent} from "../pokemon-not-found/pokemon-not-found.component";
import { PokemonFoundComponent } from '../pokemon-found/pokemon-found.component';


@Component({
  selector: 'app-pokemon-select',
  templateUrl: './pokemon-select.component.html',
  styleUrls: ['./pokemon-select.component.css']
})
export class PokemonSelectComponent implements OnInit {

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
    private areaService: AreaService,
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {

    this.pokemonService.listPokemon().subscribe((response: any) => {
      response.map((pokemon: Pokemon) => {
        this.extractPokemon(pokemon);
      });
      this.optionsPokemon = response
      console.log(this.optionsPokemon)
    });

    this.areaService.listArea().subscribe((response: any) => {
      response.map((area: Area) => {
        this.extractArea(area);
      });
      this.optionsAreas = response;
    })
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

  extractPokemon(pokemon: Pokemon) {
    return {
      id: pokemon.id,
      name: pokemon.name,
      url: pokemon.url
    };
  }

  extractArea(area: Area) {
    return {
      id: area.id,
      name: area.name,
      url: area.url
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
    this.areaService.findClosestArea(this.pokemon, this.area).subscribe((response: any) => {
      this.openDialog(response);
      }, (error: any) => {
      this.openDialogError();
      }
    );
  }
}
