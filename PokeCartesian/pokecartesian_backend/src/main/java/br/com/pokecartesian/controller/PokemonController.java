package br.com.pokecartesian.controller;

import br.com.pokecartesian.exception.EmptyListPokemonException;
import br.com.pokecartesian.service.PokemonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping(path = "/cartesianPlane")
    public ResponseEntity<?> cartesianPlane(){
        try {
            return ResponseEntity.ok(service.cartesianPlane());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(path = "/closestPokemon")
    public ResponseEntity<?> closestPokemon(){
        try {
            return ResponseEntity.ok(service.closestPokemon());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
