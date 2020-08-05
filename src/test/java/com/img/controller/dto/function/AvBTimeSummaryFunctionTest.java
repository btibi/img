package com.img.controller.dto.function;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.img.domain.Match;

public class AvBTimeSummaryFunctionTest {

    private final AvBTimeSummaryFunction summaryFunction = new AvBTimeSummaryFunction();

    @Test
    public void avBTimeSummaryFunctionReturnTextWithTimeInfoWhenMatchHasAlreadyStarted() {
        String summary = summaryFunction.apply(Match.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(LocalDateTime.now().minusMinutes(5))
                .build());

        assertEquals("Dan vs Pete started 5 minutes ago", summary);
    }

    @Test
    public void avBTimeSummaryFunctionReturnTextWithTimeInfWhenMatchWillStart() {
        String summary = summaryFunction.apply(Match.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(LocalDateTime.now().plusMinutes(5))
                .build());

        assertEquals("Dan vs Pete starts in 5 minutes", summary);
    }
}