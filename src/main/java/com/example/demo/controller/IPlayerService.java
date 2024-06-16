package com.example.demo.controller;

import com.example.demo.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();
    Player findById(String id);
    boolean save(Player player);
    boolean delete(String id);
    boolean update(String id, Player player);
}
