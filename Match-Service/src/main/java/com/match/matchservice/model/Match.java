package com.match.matchservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@ApiModel(description = "Details about a match")
public class Match {

    @ApiModelProperty(notes = "The unique ID of the match")
    private String id;

    @ApiModelProperty(notes = "The ID of the home team")
    private String homeTeamId;

    @ApiModelProperty(notes = "The ID of the away team")
    private String awayTeamId;

    @ApiModelProperty(notes = "The date of the match")
    private String date;

    @ApiModelProperty(notes = "The location of the match")
    private String location;

    @ApiModelProperty(notes = "The score of the home team")
    private int homeTeamScore;

    @ApiModelProperty(notes = "The score of the away team")
    private int awayTeamScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Match() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public Match(String homeTeamId, String awayTeamId, String date, String location, int homeTeamScore, int awayTeamScore) {
        this.id = String.valueOf(UUID.randomUUID());
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.date = date;
        this.location = location;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }
}
