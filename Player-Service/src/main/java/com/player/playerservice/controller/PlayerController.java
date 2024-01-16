package com.player.playerservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.player.playerservice.model.Player;
import com.player.playerservice.service.PlayerService;
import com.player.playerservice.util.DTOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.types.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
@Api(tags = "Player Service", description = "Service for managing players")
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ApiOperation(value = "Get player details by ID")
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForGetPlayerById")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(player);
    }

    @ApiOperation(value = "Add a new player")
    @PostMapping
    @HystrixCommand(fallbackMethod = "fallbackForAddPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update player details by ID")
    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForUpdatePlayer")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        return ResponseEntity.ok(updatedPlayer);
    }

    @ApiOperation(value = "Delete player by ID")
    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForDeletePlayer")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all players")
    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallbackForGetAllPlayers")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @ApiOperation(value = "Get all players of a given team")
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeamId(@PathVariable String teamId) {
        List<Player> players = playerService.getAllPlayersByTeam(teamId);
        return ResponseEntity.ok(DTOUtils.mapPlayerListToPlayerDTOList(players));
    }

    // Single fallback method for all endpoints
    private ResponseEntity<Player> fallbackForGetPlayerById(String id) {
        // Implement fallback logic for getPlayerById
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Player());
    }

    private ResponseEntity<Player> fallbackForAddPlayer(Player player) {
        // Implement fallback logic for addPlayer
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Player());
    }

    private ResponseEntity<Player> fallbackForUpdatePlayer(String id, Player player) {
        // Implement fallback logic for updatePlayer
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Player());
    }

    private ResponseEntity<Void> fallbackForDeletePlayer(String id) {
        // Implement fallback logic for deletePlayer
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    private ResponseEntity<List<Player>> fallbackForGetAllPlayers() {
        // Implement fallback logic for getAllPlayers
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<>());
    }
}
