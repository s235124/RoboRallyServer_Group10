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
    public Lobby getLobbyById(Integer id) {
        return lobbyRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Lobby addLobby(@RequestBody Lobby lobby) {
        return lobbyRepository.save(lobby);
    }

    @PutMapping("/{id}")
    public Lobby updateLobby(Integer id, @RequestBody Lobby lobby) {
        Lobby exists = lobbyRepository.findById(id).orElse(null);
        if (exists != null) {
            exists.setVisibility(lobby.isVisibility());
            exists.setMaxPlayerCount(lobby.getMaxPlayerCount());
            exists.setCurrentPlayerCount(lobby.getCurrentPlayerCount());
            exists.setPlayers(lobby.getPlayers());
            return lobbyRepository.save(exists);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLobby(Integer id) {
        lobbyRepository.deleteById(id);
    }

}
