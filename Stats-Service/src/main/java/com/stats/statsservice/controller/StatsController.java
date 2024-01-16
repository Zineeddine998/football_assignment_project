package com.stats.statservice.controller;

import com.stats.statservice.model.PlayerStats;
import com.stats.statservice.model.TeamStats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@Api(tags = "Stats Service", description = "Service for managing team and player statistics")
public class StatsController {

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
