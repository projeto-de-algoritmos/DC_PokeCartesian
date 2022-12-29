import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pokemons} from '../interfaces/pokemons';
import {Observable} from "rxjs";
import {ResponseDTO} from "../interfaces/ResponseDTO";

const API = 'apiPokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  constructor(private http: HttpClient) { }

  cartesianPlane(): Observable<Pokemons[]> {
    return this.http.get<Pokemons[]>(`${API}/cartesianPlane`);
  }

  closestPokemon(): Observable<ResponseDTO> {
    return this.http.get<ResponseDTO>(`${API}/closestPokemon`);
  }
}
