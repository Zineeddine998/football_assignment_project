package com.stats.statsservice.controller;

import com.stats.statservice.model.PlayerStats;
import com.stats.statservice.model.TeamStats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stats")
@Api(tags = "Stats Service", description = "Service for managing team and player statistics")
public class StatsController {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<String> getTeamStats() {
        return ResponseEntity.ok("test");
    }

    @GetMapping("/matches/test")
    public String testMatchesService() {
        String port =  this.restTemplate.getForObject("http://match-service/matches/test", String.class);
        return port;
    }

    @ApiOperation(value = "Get team statistics for the season")
    @GetMapping("/team-stats/{teamId}")
    public TeamStats getTeamStats(@PathVariable String teamId) {
        // Implement logic to retrieve team statistics for the season
        // For example purposes, generating random team statistics
        return new TeamStats(teamId);
    }

    @ApiOperation(value = "Get player statistics for the season")
    @GetMapping("/player-stats/{playerId}")
    public PlayerStats getPlayerStats(@PathVariable String playerId) {
        // Implement logic to retrieve player statistics for the season
        // For example purposes, generating random player statistics
        return new PlayerStats(playerId);
    }
}
