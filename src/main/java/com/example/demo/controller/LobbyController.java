package com.example.demo.controller;

import com.example.demo.model.Lobby;
import com.example.demo.repository.LobbyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Base endpoint
@RequestMapping("/lobbies")
public class LobbyController {

    private LobbyRepository lobbyRepository;

    public LobbyController(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @GetMapping
    public ResponseEntity<List<Lobby>> getLobbies() {
        List<Lobby> lobbies = lobbyRepository.findAll();
        if (lobbies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lobbies);
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Lobby> getLobbyById(@PathVariable Integer id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            return ResponseEntity.ok(lobby);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addLobby(@RequestBody Lobby lobby) {
        lobbyRepository.save(lobby);
        return ResponseEntity.ok("lobby created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLobby(@PathVariable Integer id, @RequestBody Lobby lobby) {
        Lobby exists = lobbyRepository.findById(id).orElse(null);
        if (exists != null) {
            exists.setVisibility(lobby.isVisibility());
            exists.setMaxPlayerCount(lobby.getMaxPlayerCount());
            exists.setCurrentPlayerCount(lobby.getCurrentPlayerCount());
            exists.setPlayers(lobby.getPlayers());
            lobbyRepository.save(exists);
            return ResponseEntity.ok("lobby successfully updated");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLobby(@PathVariable Integer id) {
        lobbyRepository.deleteById(id);
        return ResponseEntity.ok("lobby successfully deleted");
    }

}
