import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Pokemons} from "../interfaces/pokemons";
export interface DialogData {
  pokemon: Pokemons;
}

@Component({
  selector: 'app-pokemon-found',
  templateUrl: './pokemon-found.component.html',
  styleUrls: ['./pokemon-found.component.css']
})
export class PokemonFoundComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<PokemonFoundComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  resposta: any = this.data

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    console.log(this.resposta.resposta)
  }
}
