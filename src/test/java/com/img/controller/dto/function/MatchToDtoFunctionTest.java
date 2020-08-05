package com.img.controller.dto.function;

import static org.junit.Assert.assertEquals;

import static com.img.controller.dto.SummaryType.AvB;
import static com.img.controller.dto.SummaryType.AvBTime;

import java.time.LocalDateTime;

import org.junit.Test;

import com.img.controller.dto.MatchDto;
import com.img.domain.Match;

public class MatchToDtoFunctionTest {

    @Test
    public void matchToDtoFunctionWhenSummaryTypeIsAvB() {
        LocalDateTime now = LocalDateTime.now();
        MatchDto expectedDto = MatchDto.builder().matchId(1).playerA("Dan").playerB("Pete").startDate(now).summary(
                "Dan vs Pete").build();

        MatchDto matchDto = new MatchToDtoFunction(AvB).apply(Match.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(now)
                .build());

        assertEquals(expectedDto, matchDto);
    }

    @Test
    public void matchToDtoFunctionWhenSummaryTypeIsAvBTimeAndMatchHasAlreadyStarted() {
        LocalDateTime fiveMinsAgo = LocalDateTime.now().minusMinutes(5);
        MatchDto expectedDto = MatchDto.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(fiveMinsAgo)
                .summary("Dan vs Pete started 5 minutes ago")
                .build();

        MatchDto matchDto = new MatchToDtoFunction(AvBTime).apply(Match.builder().matchId(1).playerA("Dan").playerB(
                "Pete").startDate(fiveMinsAgo).build());

        assertEquals(expectedDto, matchDto);
    }

    @Test
    public void matchToDtoFunctionWhenSummaryTypeIsAvBTimeAndMatchHasNotStartedYet() {
        LocalDateTime fiveMinsAgo = LocalDateTime.now().plusMinutes(5);
        MatchDto expectedDto = MatchDto.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(fiveMinsAgo)
                .summary("Dan vs Pete starts in 5 minutes")
                .build();

        MatchDto matchDto = new MatchToDtoFunction(AvBTime).apply(Match.builder().matchId(1).playerA("Dan").playerB(
                "Pete").startDate(fiveMinsAgo).build());

        assertEquals(expectedDto, matchDto);
    }
}