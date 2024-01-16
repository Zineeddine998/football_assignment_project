package com.team.teamservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

// Import the Player class

@FeignClient(name = "player-service")
public interface PlayerServiceClient {
    @GetMapping("/players/team/{teamId}")
    public default List<Object> getPlayersByTeamId(@PathVariable String teamId) {
        return new ArrayList<>();
    }
}
