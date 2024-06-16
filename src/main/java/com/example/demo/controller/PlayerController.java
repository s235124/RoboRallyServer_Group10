package com.example.demo.controller;

import com.example.demo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//Base endpoint
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAll();
        return ResponseEntity.ok(players);
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayerById(String id) {
        Player player = playerService.findById(id);
        return ResponseEntity.ok().body(player);
    }

    @GetMapping
    @RequestMapping(value = "/NoP")
    public ResponseEntity<Integer> getNumberOfPlayers () {
        List<Player> players = playerService.findAll();
        return ResponseEntity.ok(players.size());
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody Player player) {
        boolean added = playerService.save(player);
        if (added)
            return ResponseEntity.ok("Player added successfully");
        else
            return ResponseEntity.internalServerError().body("Error saving player");
    }

    public ResponseEntity<String> updatePlayer(String id, @RequestBody Player player) {
        playerService.update(id, player);
        return ResponseEntity.ok("Player updated successfully");
    }

    public ResponseEntity<String> deletePlayer(String id) {
        playerService.delete(id);
        return ResponseEntity.ok("Player deleted successfully");
    }
}
