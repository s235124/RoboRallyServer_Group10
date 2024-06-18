package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lobbies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer lobbyID;

    int maxPlayerCount;
    int currentPlayerCount;

    boolean visibility;

    @OneToMany(mappedBy = "currentLobby")
    List<Player> players;
}
