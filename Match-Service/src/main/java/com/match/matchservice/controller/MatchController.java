package com.match.matchservice.controller;

import com.match.matchservice.model.Match;
import com.match.matchservice.service.MatchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@Api(tags = "Match Service", description = "Service for managing football matches")
public class MatchController {

    @Autowired
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
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

    // Single fallback method for all endpoints

    private ResponseEntity<?> fallbackForAllEndpoints() {
        // Implement fallback logic, e.g., return "Service Unavailable" with appropriate status code
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable");
    }
}
