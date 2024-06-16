package com.example.demo.controller;

import com.example.demo.model.Lobby;

import java.util.List;

public interface ILobbyService {
    List<Lobby> findAll();
    Lobby findById(Integer id);
    boolean save(Lobby lobby);
    boolean delete(Integer id);
    boolean update(Integer id, Lobby lobby);
}
