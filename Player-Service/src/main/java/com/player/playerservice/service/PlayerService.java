package com.player.playerservice.service;

import com.player.playerservice.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final List<Player> players = new ArrayList<>();

    public PlayerService() {
        // Initialize with some static data for demonstration purposes
        players.add(new Player("1", "John", "Doe"));
        players.add(new Player("2",  "Jane", "Smith"));
    }

    public Player getPlayerById(String id) {
        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Player addPlayer(Player player) {
        // Generate a unique ID (you can modify this logic based on your requirements)
        player.setId(String.valueOf(players.size() + 1));
        players.add(player);
        return player;
    }

    public Player updatePlayer(String id, Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getId().equals(id)) {
                // Update player details
                player.setTeamId(updatedPlayer.getTeamId());
                player.setFirstName(updatedPlayer.getFirstName());
                player.setLastName(updatedPlayer.getLastName());
                return player;
            }
        }
        return null; // Player not found
    }

    public void deletePlayer(String id) {
        players.removeIf(player -> player.getId().equals(id));
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }


    public List<Player> getAllPlayersByTeam(String teamId) {
        return players.stream().filter(player -> player.getTeamId().equals(teamId))
                .collect(Collectors.toList());
    }

}
