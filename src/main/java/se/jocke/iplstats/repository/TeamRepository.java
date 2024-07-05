package se.jocke.iplstats.repository;

import org.springframework.data.repository.CrudRepository;
import se.jocke.iplstats.model.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    // Spring recognizes "findBy" automatically and does lots of good things
    // such as search the db table for the first team (if we make a List<Team> instead, it gives us all)
    // with teamName
    Team findByTeamName(String teamName);

    List<Team> findAll();

}
