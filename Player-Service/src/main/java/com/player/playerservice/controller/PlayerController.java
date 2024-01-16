package com.player.playerservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.player.playerservice.model.Player;
import com.player.playerservice.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(player);
    }

    @ApiOperation(value = "Add a new player")
    @PostMapping
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update player details by ID")
    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        return ResponseEntity.ok(updatedPlayer);
    }

    @ApiOperation(value = "Delete player by ID")
    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all players")
    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    // Single fallback method for all endpoints

    private ResponseEntity<?> fallbackForAllEndpoints() {
        // Implement fallback logic, e.g., return "Service Unavailable" with appropriate status code
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable");
    }
}
