package se.jocke.iplstats.data;

import org.springframework.batch.item.ItemProcessor;
import se.jocke.iplstats.model.Match;
import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor <MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        final Match match = new Match();

        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setYear(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
        match.setVenue(matchInput.getVenue());

        // Set Team 1 & Team 2 depending on the innings order
        String firstInningsTeam;
        String secondInningsTeam;
        if ("bat".equals(matchInput.getTossDecision())) {
            firstInningsTeam = matchInput.getTossWinner();
            secondInningsTeam = matchInput.getTossWinner().equals(matchInput.getTeam1()) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        } else {
            secondInningsTeam = matchInput.getTossWinner();
            firstInningsTeam = matchInput.getTossWinner().equals(matchInput.getTeam1()) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);

        match.setTossWinner(matchInput.getTossWinner());
        match.setTossDecision(matchInput.getTossDecision());
        match.setMatchWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResultMargin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }
}
