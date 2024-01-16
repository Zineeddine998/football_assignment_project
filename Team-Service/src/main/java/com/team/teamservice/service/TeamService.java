package com.team.teamservice.service;

import com.team.teamservice.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final List<Team> teams = new ArrayList<>();

    public TeamService() {
        // Initialize with some static data for demonstration purposes
        teams.add(new Team("TeamA"));
        teams.add(new Team("TeamB"));
    }

    public Team getTeamById(String id) {
        return teams.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Team addTeam(Team team) {
        // Generate a unique ID (you can modify this logic based on your requirements)
        team.setId(String.valueOf(teams.size() + 1));
        teams.add(team);
        return team;
    }

    public Team updateTeam(String id, Team updatedTeam) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                // Update team details
                team.setName(updatedTeam.getName());
                return team;
            }
        }
        return null; // Team not found
    }

    public void deleteTeam(String id) {
        teams.removeIf(team -> team.getId().equals(id));
    }

    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }
}
