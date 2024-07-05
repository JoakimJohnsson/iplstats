package se.jocke.iplstats.controller;

import org.springframework.web.bind.annotation.*;
import se.jocke.iplstats.model.Match;
import se.jocke.iplstats.model.Team;
import se.jocke.iplstats.repository.MatchRepository;
import se.jocke.iplstats.repository.TeamRepository;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/api/teams")
    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    @GetMapping("/api/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        // This the endpoint - it calls the repository to provide the team (by team name)
        Team team = this.teamRepository.findByTeamName(teamName);
        if (team != null) {
            team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 9));
            return team;
        }
        return null;
    }

    @GetMapping("/api/{teamName}/matches")
    public List<Match> getMatchesByTeamAndYear(@PathVariable String teamName, @RequestParam int year) {
        return this.matchRepository.findAllMatchesByTeamAndYear(teamName, year);
    }
}
