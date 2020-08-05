package com.img.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.controller.dto.MatchDto;
import com.img.controller.dto.SummaryType;
import com.img.controller.dto.function.MatchToDtoFunction;
import com.img.repository.LicenseRepository;

@Service
public class MatchesService {
    private final LicenseRepository licenseRepository;

    @Autowired
    public MatchesService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public MatchDto[] licensedMatchesByCustomerIds(SummaryType summaryType, long... customerIds) {
        MatchesCollectorLicenseVisitor matchesCollectorLicenseVisitor = new MatchesCollectorLicenseVisitor();

        licenseRepository.findByCustomerIds(customerIds).forEach(
                license -> license.accept(matchesCollectorLicenseVisitor));

        return matchesCollectorLicenseVisitor.getMatches().stream().map(new MatchToDtoFunction(summaryType)).toArray(
                MatchDto[]::new);
    }
}
