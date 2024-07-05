package se.jocke.iplstats.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import se.jocke.iplstats.model.Match;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    // Spring recognizes "getBy" automatically and does lots of good things
    // such as search the db table for matches where fieldname team1 equals teamName1 or
    // field name team2 equals teamName2 - it also handles order by!
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
    List<Match> getByTeam1AndYearEqualsOrTeam2AndYearEqualsOrderByDateDesc(String teamName1, int year, String teamName2, int year2);

    // findTop5... works fine if you want to do it that way
    // List<Match> findTop5ByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

    default List<Match> findAllMatchesByTeamAndYear(String teamName, int year) {
        return getByTeam1AndYearEqualsOrTeam2AndYearEqualsOrderByDateDesc(teamName, year, teamName, year);
    }
}
