package com.team.teamservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Import the PlayerDTO class
import org.types.PlayerDTO;

@FeignClient(name = "player-service")
public interface PlayerServiceClient {

    @GetMapping("/players/team/{teamId}")
    List<PlayerDTO> getPlayersByTeamId(@PathVariable("teamId") String teamId);
}
