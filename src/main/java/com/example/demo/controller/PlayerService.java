package com.example.demo.controller;

import com.example.demo.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    ArrayList<Player> players = new ArrayList<>();

    public PlayerService() {
        players.add(new Player("Player 1", "3,5,1,2,3", "Red"));
        players.add(new Player("Player 2", "5,2,1,1,6", "Blue"));
    }

    @Override
    public List<Player> findAll() {
        return players;
    }

    @Override
    public Player findById(String id) {
        for(Player p : players) {
            if(p.getPlayerName().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean save(Player player) {
        players.add(player);
        return true;
    }

    @Override
    public boolean delete(String id) {
        ArrayList<Player> newProducts = new ArrayList<Player>();
        int oldSize = players.size();
        players.forEach((player -> {
            if(!player.getPlayerName().equals(id))
                newProducts.add(player);
        }));
        players = newProducts;
        return oldSize < players.size();
    }

    @Override
    public boolean update(String id, Player player) {
        for(Player currPlayer : players) {
            if(currPlayer.getPlayerName().equals(id)) {
                currPlayer.setPlayerName(player.getPlayerName());
                currPlayer.setCards(player.getCards());
                currPlayer.setColor(player.getColor());
                return true;
            }
        }
        return false;
    }
}
