package com.img.domain;

public interface LicenseVisitor {
    void visit(MatchLicense matchLicense);

    void visit(TournamentLicense tournamentLicense);
}
