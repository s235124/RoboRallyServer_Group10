package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Base endpoint
@RequestMapping("lobbies/{id}/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(players);
    }

    @GetMapping(value = "/{playerid}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer playerid) {
        Player player = playerRepository.findById(playerid).orElse(null);
        if (player != null) {
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @RequestMapping(value = "/NoP")
    public ResponseEntity<Integer> getNumberOfPlayers () {
        int size = playerRepository.findAll().size();
        return ResponseEntity.ok(size);
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return ResponseEntity.ok("Player added successfully");
    }

    @PutMapping("/{playerid}")
    public ResponseEntity<String> updatePlayer(@PathVariable Integer playerid, @RequestBody Player player) {
        Player exists = playerRepository.findById(playerid).orElse(null);
        if (exists != null) {
            exists.setPlayerName(player.getPlayerName());
            exists.setCardStr(player.getCardStr());
            exists.setColor(player.getColor());
            exists.setCurrentLobby(player.getCurrentLobby());
            playerRepository.save(player);
            return ResponseEntity.ok("lobby successfully updated");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{playerid}/cardstr")
    public ResponseEntity<String> updateCardStrOnPlayer (@PathVariable Integer playerid, @RequestBody String cardStr) {
        Player exists = playerRepository.findById(playerid).orElse(null);
        if (exists != null) {
            exists.setCardStr(cardStr);
            playerRepository.save(exists);
            return ResponseEntity.ok("lobby successfully updated");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{playerid}")
    public ResponseEntity<String> deletePlayer(@PathVariable Integer playerid) {
        playerRepository.deleteById(playerid);
        return ResponseEntity.ok("Player deleted successfully");
    }
}
