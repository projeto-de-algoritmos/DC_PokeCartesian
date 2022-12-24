import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pokemons} from '../interfaces/pokemons';
import {Observable} from "rxjs";

const API = 'apiPokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  constructor(private http: HttpClient) { }

  listPokemon(): Observable<Pokemons> {
    return this.http.get<Pokemons>(`${API}/pokemons`);
  }
}
