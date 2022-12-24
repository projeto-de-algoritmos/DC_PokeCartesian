import {Component, Inject} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { DialogData } from '../pokemon-found/pokemon-found.component';


@Component({
  selector: 'app-pokemon-not-found',
  templateUrl: './pokemon-not-found.component.html',
  styleUrls: ['./pokemon-not-found.component.css']
})
export class PokemonNotFoundComponent {
  constructor(public dialogRef: MatDialogRef<PokemonNotFoundComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
