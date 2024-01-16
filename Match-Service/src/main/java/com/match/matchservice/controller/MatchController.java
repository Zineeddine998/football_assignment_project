package com.match.matchservice.controller;

import com.match.matchservice.model.Match;
import com.match.matchservice.service.MatchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.netflix.config.DeploymentContext.ContextKey.environment;

@RestController
@RequestMapping("/matches")
@Api(tags = "Match Service", description = "Service for managing football matches")
public class MatchController {

    @Autowired
    private final MatchService matchService;

    @Autowired
    Environment environment;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testLoadBalancing() {
        String serverPort = environment.getProperty("local.server.port");
        return ResponseEntity.ok(serverPort);
    }

    @ApiOperation(value = "Get match details by ID")
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Match> getMatchById(@PathVariable String id) {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @ApiOperation(value = "Add a new match")
    @PostMapping
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        Match savedMatch = matchService.addMatch(match);
        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update match details by ID")
    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Match> updateMatch(@PathVariable String id, @RequestBody Match match) {
        Match updatedMatch = matchService.updateMatch(id, match);
        return ResponseEntity.ok(updatedMatch);
    }

    @ApiOperation(value = "Delete match by ID")
    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
        matchService.deleteMatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all matches")
    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallbackForAllEndpoints")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    // Inside MatchController class

// ...

    // Single fallback method for all endpoints
    private ResponseEntity<Match> fallbackForGetMatchById(String id) {
        // Implement fallback logic for getMatchById
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Match());
    }

    private ResponseEntity<Match> fallbackForAddMatch(Match match) {
        // Implement fallback logic for addMatch
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Match());
    }

    private ResponseEntity<Match> fallbackForUpdateMatch(String id, Match match) {
        // Implement fallback logic for updateMatch
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Match());
    }

    private ResponseEntity<Void> fallbackForDeleteMatch(String id) {
        // Implement fallback logic for deleteMatch
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    private ResponseEntity<List<Match>> fallbackForGetAllMatches() {
        // Implement fallback logic for getAllMatches
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<>());
    }

// ...

}
