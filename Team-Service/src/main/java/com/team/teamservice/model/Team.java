package com.team.teamservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApiModel(description = "Details about a team")
public class Team {

    @ApiModelProperty(notes = "The unique ID of the team")
    private String id;

    @ApiModelProperty(notes = "The first name of the team")
    private String name;

    @ApiModelProperty(notes = "list of players")
    private List<Object> playersList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public Team(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
    }
}
