package com.stats.statservice.service;

import com.stats.statservice.model.PlayerStats;
import com.stats.statservice.model.TeamStats;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    // This method retrieves team statistics for the season
    public TeamStats getTeamStats(String teamId) {
        // Implement logic to retrieve team statistics for the season
        // For example purposes, generating random team statistics
        return new TeamStats(teamId);
    }

    // This method retrieves player statistics for the season
    public PlayerStats getPlayerStats(String playerId) {
        // Implement logic to retrieve player statistics for the season
        // For example purposes, generating random player statistics
        return new PlayerStats(playerId);
    }
}
