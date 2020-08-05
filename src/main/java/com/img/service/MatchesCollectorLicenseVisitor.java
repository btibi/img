package com.img.service;

import java.util.HashSet;
import java.util.Set;

import com.img.domain.LicenseVisitor;
import com.img.domain.Match;
import com.img.domain.MatchLicense;
import com.img.domain.TournamentLicense;

import lombok.Getter;

/**
 * {@link LicenseVisitor} implementation to collect the matches form the {@link com.img.domain.License} classes.
 */
public class MatchesCollectorLicenseVisitor implements LicenseVisitor {
    @Getter
    private final Set<Match> matches = new HashSet<>();

    @Override
    public void visit(MatchLicense matchLicense) {
        matches.add(matchLicense.getMatch());
    }

    @Override
    public void visit(TournamentLicense tournamentLicense) {
        matches.addAll(tournamentLicense.getTournament().getMatches());
    }
}
