package com.example.demo.controller;

import com.example.demo.model.Lobby;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LobbyService implements ILobbyService {

    ArrayList<Lobby> lobbies = new ArrayList<>();


    public LobbyService() {
        lobbies.add(new Lobby(1, 4, 2, true));
        lobbies.add(new Lobby(2, 5, 1, true));
    }

    @Override
    public List<Lobby> findAll() {
        return lobbies;
    }

    @Override
    public Lobby findById(Integer id) {
        for(Lobby l : lobbies) {
            if(l.getLobbyID().equals(id)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public boolean save(Lobby lobby) {
        lobbies.add(lobby);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        ArrayList<Lobby> newProducts = new ArrayList<Lobby>();
        int oldSize = lobbies.size();
        lobbies.forEach((lobby -> {
            if(!lobby.getLobbyID().equals(id))
                newProducts.add(lobby);
        }));
        lobbies = newProducts;
        return oldSize < lobbies.size();
    }

    @Override
    public boolean update(Integer id, Lobby lobby) {
        for(Lobby currLobby : lobbies) {
            if(currLobby.getLobbyID().equals(id)) {
                currLobby.setLobbyID(lobby.getLobbyID());
                currLobby.setMaxPlayerCount(lobby.getMaxPlayerCount());
                currLobby.setCurrentPlayerCount(lobby.getCurrentPlayerCount());
                currLobby.setVisibility(lobby.isVisibility());
                return true;
            }
        }
        return false;
    }
}
