package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String playerName;

    String cards;

    String color;
}
