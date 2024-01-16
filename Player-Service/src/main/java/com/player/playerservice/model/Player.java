package com.player.playerservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@ApiModel(description = "Details about a player")
public class Player {

    @ApiModelProperty(notes = "The unique ID of the player")
    private String id;

    @ApiModelProperty(notes = "The team ID to which the player belongs")
    private String teamId;

    @ApiModelProperty(notes = "The first name of the player")
    private String firstName;

    @ApiModelProperty(notes = "The last name of the player")
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Player() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public Player(String teamId, String firstName, String lastName) {
        this.id = String.valueOf(UUID.randomUUID());
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
