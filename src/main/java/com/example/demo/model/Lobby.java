package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lobbies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer lobbyID;

    int maxPlayerCount;
    int currentPlayerCount;

    boolean visibility;

}
