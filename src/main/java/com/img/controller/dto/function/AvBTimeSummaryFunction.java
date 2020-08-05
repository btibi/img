package com.img.controller.dto.function;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import com.img.domain.Match;

/**
 * Create A vs B summary text with time info.
 * <p>
 * <pre>
 * It support two different format:
 * A vs B starts in X minutes - when the match has not started yet
 * A vs B started X minutes ago - when the match has already started
 * <pre/>
 */
public class AvBTimeSummaryFunction implements Function<Match, String> {
    @Override
    public String apply(Match match) {
        long diffInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), match.getStartDate());

        return String.format("%s vs %s " + (diffInMinutes > 0 ? "starts in %s minutes" : "started %s minutes ago"),
                match.getPlayerA(), match.getPlayerB(), Math.abs(diffInMinutes));
    }
}
