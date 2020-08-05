package com.img.controller.dto;

import java.util.function.Function;

import com.img.controller.dto.function.AvBSummaryFunction;
import com.img.controller.dto.function.AvBTimeSummaryFunction;
import com.img.domain.Match;

public enum SummaryType {
    AvB(new AvBSummaryFunction()),
    AvBTime(new AvBTimeSummaryFunction());

    private final Function<Match, String> summaryFunction;

    SummaryType(Function<Match, String> summaryFunction) {
        this.summaryFunction = summaryFunction;
    }

    public String summary(Match match) {
        return summaryFunction.apply(match);
    }
}
