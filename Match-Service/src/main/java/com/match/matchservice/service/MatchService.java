package com.match.matchservice.service;

import com.match.matchservice.model.Match;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final List<Match> matches = new ArrayList<>();

    public MatchService() {
        // Initialize with some static data for demonstration purposes
        matches.add(new Match( "TeamA", "TeamB", "2024-01-01", "StadiumA", 2, 1));
        matches.add(new Match( "TeamC", "TeamD", "2024-02-01", "StadiumB", 0, 0));
    }

    public Match getMatchById(String id) {
        return matches.stream()
                .filter(match -> match.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Match addMatch(Match match) {
        // Generate a unique ID (you can modify this logic based on your requirements)
        match.setId(String.valueOf(matches.size() + 1));
        matches.add(match);
        return match;
    }

    public Match updateMatch(String id, Match updatedMatch) {
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.getId().equals(id)) {
                // Update match details
                match.setHomeTeamId(updatedMatch.getHomeTeamId());
                match.setAwayTeamId(updatedMatch.getAwayTeamId());
                match.setDate(updatedMatch.getDate());
                match.setLocation(updatedMatch.getLocation());
                match.setHomeTeamScore(updatedMatch.getHomeTeamScore());
                match.setAwayTeamScore(updatedMatch.getAwayTeamScore());
                return match;
            }
        }
        return null; // Match not found
    }

    public void deleteMatch(String id) {
        matches.removeIf(match -> match.getId().equals(id));
    }

    public List<Match> getAllMatches() {
        return new ArrayList<>(matches);
    }
}
