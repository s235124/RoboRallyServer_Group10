package com.example.demo.controller;

import com.example.demo.model.Lobby;
import com.example.demo.model.Player;
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

    @GetMapping(value = "/{id}/inc")
    public ResponseEntity<String> incrementPlayerCount (@PathVariable Integer id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            lobby.setCurrentPlayerCount(lobby.getCurrentPlayerCount() + 1);
            lobbyRepository.save(lobby);
            return ResponseEntity.ok(lobby.toString());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/NoL")
    public ResponseEntity<Integer> getNoLobbies() {
        List<Lobby> lobbies = lobbyRepository.findAll();
        return ResponseEntity.ok(lobbies.size());
    }

    @GetMapping(value = "/{id}/getplayers")
    public ResponseEntity<List<Player>> getPlayers(@PathVariable Integer id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            return ResponseEntity.ok(lobby.getPlayers());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}/isready")
    public ResponseEntity<Boolean> isReady(@PathVariable int id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            if (lobby.isReady()) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}/ready")
    public ResponseEntity<Boolean> setReady(@PathVariable int id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            if (lobby.getCurrentPlayerCount() >= 2) {
                lobby.setReady(true);
                lobbyRepository.save(lobby);
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.ok(false);
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
            exists.setPlayerColors(lobby.getPlayerColors());
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

    @GetMapping("/{id}/colors")
    public ResponseEntity<String> getColors(@PathVariable Integer id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        if (lobby != null) {
            return ResponseEntity.ok(lobby.getPlayerColors());
        }
        return ResponseEntity.notFound().build();
    }
}
