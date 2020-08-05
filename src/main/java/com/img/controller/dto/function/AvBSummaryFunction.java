package com.img.controller.dto.function;

import java.util.function.Function;

import com.img.domain.Match;

/**
 * Create A vs B summary text
 */
public class AvBSummaryFunction implements Function<Match, String> {
    @Override
    public String apply(Match match) {
        return String.format("%s vs %s", match.getPlayerA(), match.getPlayerB());
    }
}
