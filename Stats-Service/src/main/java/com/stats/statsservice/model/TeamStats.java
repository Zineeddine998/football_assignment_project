package com.stats.statservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Random;

@ApiModel(description = "Team statistics for the season")
public class TeamStats {

    @ApiModelProperty(notes = "The team ID")
    private String teamId;

    @ApiModelProperty(notes = "Total goals scored by the team")
    private int goalsScored;

    @ApiModelProperty(notes = "Total goals conceded by the team")
    private int goalsConceded;

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @ApiModelProperty(notes = "Total matches played by the team")
    private int matchesPlayed;

    public TeamStats(String teamId) {
        this.teamId = teamId;
        this.goalsScored = new Random().nextInt(50);       // Random goals scored (for example purposes)
        this.goalsConceded = new Random().nextInt(30);    // Random goals conceded (for example purposes)
        this.matchesPlayed = new Random().nextInt(40);    // Random matches played (for example purposes)
    }

    // Getters and setters
}
