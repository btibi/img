package com.img.controller.dto.function;

import java.util.function.Function;

import com.img.controller.dto.MatchDto;
import com.img.controller.dto.SummaryType;
import com.img.domain.Match;

/**
 * Convert {@link Match} entity to {@link MatchDto}
 */
public class MatchToDtoFunction implements Function<Match, MatchDto> {
    private final SummaryType summaryType;

    public MatchToDtoFunction(SummaryType summaryType) {
        this.summaryType = summaryType;
    }

    @Override
    public MatchDto apply(Match match) {
        return MatchDto.builder().matchId(match.getMatchId()).startDate(match.getStartDate()).playerA(
                match.getPlayerA()).playerB(match.getPlayerB()).summary(summaryType.summary(match)).build();
    }
}
