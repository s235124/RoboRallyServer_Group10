package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
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

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping(value = "/{playerid}")
    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    @GetMapping
    @RequestMapping(value = "/NoP")
    public int getNumberOfPlayers () {
        return playerRepository.findAll().size();
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/{playerid}")
    public Player updatePlayer(Integer id, @RequestBody Player player) {
        Player exists = playerRepository.findById(id).orElse(null);
        if (exists != null) {
            exists.setPlayerName(player.getPlayerName());
            exists.setCardStr(player.getCardStr());
            exists.setColor(player.getColor());
            exists.setCurrentLobby(player.getCurrentLobby());
            return playerRepository.save(player);
        }
        return null;
    }

    @DeleteMapping("/{playerid}")
    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}
