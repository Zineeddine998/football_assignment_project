package com.stats.statservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Random;

@ApiModel(description = "Player statistics for the season")
public class PlayerStats {

    @ApiModelProperty(notes = "The player ID")
    private String playerId;

    @ApiModelProperty(notes = "Total goals scored by the player")
    private int goalsScored;

    @ApiModelProperty(notes = "Total assists provided by the player")
    private int assists;

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @ApiModelProperty(notes = "Total matches played by the player")
    private int matchesPlayed;

    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.goalsScored = new Random().nextInt(20); // Random goals scored (for example purposes)
        this.assists = new Random().nextInt(15);    // Random assists (for example purposes)
        this.matchesPlayed = new Random().nextInt(30); // Random matches played (for example purposes)
    }

    // Getters and setters
}

