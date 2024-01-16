package com.team.teamservice.model;

import com.team.teamservice.model.Team;
import org.types.PlayerDTO;

import java.util.List;

public class TeamStructure {
    private Team team;
    private List<PlayerDTO> players;

    // Constructors, getters, setters...

    public TeamStructure(Team team, List<PlayerDTO> players) {
        this.team = team;
        this.players = players;
    }

    // Getter and Setter methods
}