package com.example.demo.controller;

import com.example.demo.model.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
//Base endpoint
@RequestMapping("/lobbies")
public class LobbyController {

    @Autowired
    private ILobbyService lobbyService;

    @GetMapping
    public ResponseEntity<List<Lobby>> getLobbies() {
        List<Lobby> lobbies = lobbyService.findAll();
        if (lobbies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lobbies);
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Lobby> getLobbyById(Integer id) {
        Lobby lobby = lobbyService.findById(id);
        return ResponseEntity.ok(lobby);
    }
}
