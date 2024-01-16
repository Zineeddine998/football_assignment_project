package com.team.teamservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.team.teamservice.feign.PlayerServiceClient;
import com.team.teamservice.model.Team;
import com.team.teamservice.model.TeamStructure;
import com.team.teamservice.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.types.PlayerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/teams")
@Api(tags = "Team Service", description = "Service for managing football teams")
public class TeamController {

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final PlayerServiceClient playerServiceClient;

    @Autowired
    public TeamController(TeamService teamService, PlayerServiceClient playerServiceClient) {
        this.teamService = teamService;
        this.playerServiceClient = playerServiceClient;
    }

    @ApiOperation(value = "Get team details by ID")
    @GetMapping("/{teamId}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<TeamStructure> getTeamById(@PathVariable("teamId") String teamId) {
        Team team = teamService.getTeamById(teamId);
        List<PlayerDTO> listOfPlayers = playerServiceClient.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(new TeamStructure(team, listOfPlayers));
    }

    @ApiOperation(value = "Add a new team")
    @PostMapping
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.addTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update team details by ID")
    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return ResponseEntity.ok(updatedTeam);
    }

    @ApiOperation(value = "Delete team by ID")
    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all teams")
    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    private ResponseEntity<Team> fallbackForGetTeamById(String id) {
        // Implement fallback logic for getTeamById
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Team());
    }

    private ResponseEntity<Team> fallbackForAddTeam(Team team) {
        // Implement fallback logic for addTeam
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Team());
    }

    private ResponseEntity<Team> fallbackForUpdateTeam(String id, Team team) {
        // Implement fallback logic for updateTeam
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Team());
    }

    private ResponseEntity<Void> fallbackForDeleteTeam(String id) {
        // Implement fallback logic for deleteTeam
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    private ResponseEntity<List<Team>> fallbackForGetAllTeams() {
        // Implement fallback logic for getAllTeams
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<>());
    }

}
