package com.img.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.img.domain.Match;
import com.img.domain.MatchLicense;
import com.img.domain.Tournament;
import com.img.domain.TournamentLicense;

public class MatchesCollectorLicenseVisitorTest {

    private MatchesCollectorLicenseVisitor matchesCollectorLicenseVisitor;

    @Before
    public void setUp() {
        matchesCollectorLicenseVisitor = new MatchesCollectorLicenseVisitor();
    }

    @Test
    public void matchesCollectorLicenseVisitorCollectMatchesFromMatchLicense() {
        Match match = Match.builder().matchId(1).build();
        MatchLicense matchLicense = MatchLicense.builder().match(match).build();

        matchesCollectorLicenseVisitor.visit(matchLicense);

        assertThat(matchesCollectorLicenseVisitor.getMatches().size(), is(1));
        assertThat(matchesCollectorLicenseVisitor.getMatches(), contains(match));
    }

    @Test
    public void matchesCollectorLicenseVisitorCollectMatchesFromTournamentLicense() {
        Match match1 = Match.builder().matchId(1).build();
        Match match2 = Match.builder().matchId(2).build();
        TournamentLicense tournamentLicense = TournamentLicense.builder().tournament(
                Tournament.builder().matches(Arrays.asList(match1, match2)).build()).build();

        matchesCollectorLicenseVisitor.visit(tournamentLicense);

        assertThat(matchesCollectorLicenseVisitor.getMatches().size(), is(2));
        assertThat(matchesCollectorLicenseVisitor.getMatches(), containsInAnyOrder(match1, match2));
    }
}