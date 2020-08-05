package com.img.controller.dto.function;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.img.domain.Match;

public class AvBSummaryFunctionTest {

    private final AvBSummaryFunction summaryFunction = new AvBSummaryFunction();

    @Test
    public void avBSummaryFunctionReturnSimpleVsText() {
        String summary = summaryFunction.apply(Match.builder().matchId(1).playerA("Dan").playerB("Pete").build());

        assertEquals("Dan vs Pete", summary);
    }
}