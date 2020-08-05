package com.img.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.img.controller.dto.MatchDto;
import com.img.controller.dto.SummaryType;
import com.img.domain.Match;
import com.img.domain.MatchLicense;
import com.img.domain.Tournament;
import com.img.domain.TournamentLicense;
import com.img.repository.LicenseRepository;

@RunWith(MockitoJUnitRunner.class)
public class MatchesServiceTest {

    private MatchesService matchesService;

    @Mock
    private LicenseRepository licenseRepository;

    @Before
    public void setUp() {
        matchesService = new MatchesService(licenseRepository);
    }

    @Test
    public void licensedMatchesByCustomerIdsWhenCustomerLicensedToMatches() {
        when(licenseRepository.findByCustomerIds(1)).thenReturn(
                Arrays.asList(MatchLicense.builder().match(Match.builder()
                        .matchId(1)
                        .playerA("Dan")
                        .playerB("Pete")
                        .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                        .build()).build(), MatchLicense.builder().match(Match.builder()
                        .matchId(2)
                        .playerA("Matt")
                        .playerB("Chris")
                        .startDate(LocalDateTime.of(2020, 8, 6, 16, 0))
                        .build()).build()));

        MatchDto[] matchDtos = matchesService.licensedMatchesByCustomerIds(SummaryType.AvB, 1);

        assertThat(matchDtos, arrayContainingInAnyOrder(MatchDto.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                .summary("Dan vs Pete")
                .build(), MatchDto.builder().matchId(2).playerA("Matt").playerB("Chris").startDate(
                LocalDateTime.of(2020, 8, 6, 16, 0)).summary("Matt vs Chris").build()));
    }

    @Test
    public void licensedMatchesByCustomerIdsWhenCustomerLicensedToTournament() {
        when(licenseRepository.findByCustomerIds(1)).thenReturn(Collections.singletonList(TournamentLicense.builder()
                .tournament(Tournament.builder().matches(Arrays.asList(Match.builder()
                        .matchId(1)
                        .playerA("Dan")
                        .playerB("Pete")
                        .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                        .build(), Match.builder()
                        .matchId(2)
                        .playerA("Matt")
                        .playerB("Chris")
                        .startDate(LocalDateTime.of(2020, 8, 6, 16, 0))
                        .build())).build())
                .build()));

        MatchDto[] matchDtos = matchesService.licensedMatchesByCustomerIds(SummaryType.AvB, 1);

        assertThat(matchDtos, arrayContainingInAnyOrder(MatchDto.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                .summary("Dan vs Pete")
                .build(), MatchDto.builder().matchId(2).playerA("Matt").playerB("Chris").startDate(
                LocalDateTime.of(2020, 8, 6, 16, 0)).summary("Matt vs Chris").build()));
    }

    @Test
    public void licensedMatchesByCustomerIdsWhenCustomerLicensedToTournamentAnMatch() {
        when(licenseRepository.findByCustomerIds(1)).thenReturn(Arrays.asList(TournamentLicense.builder()
                .tournament(Tournament.builder().matches(Arrays.asList(Match.builder()
                        .matchId(1)
                        .playerA("Dan")
                        .playerB("Pete")
                        .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                        .build(), Match.builder()
                        .matchId(2)
                        .playerA("Matt")
                        .playerB("Chris")
                        .startDate(LocalDateTime.of(2020, 8, 6, 16, 0))
                        .build())).build())
                .build(), MatchLicense.builder().match(Match.builder()
                .matchId(3)
                .playerA("Steve")
                .playerB("John")
                .startDate(LocalDateTime.of(2020, 8, 7, 14, 0))
                .build()).build(), MatchLicense.builder().match(Match.builder().matchId(4).playerA("Rob").playerB(
                "James").startDate(LocalDateTime.of(2020, 8, 7, 16, 0)).build()).build()));

        MatchDto[] matchDtos = matchesService.licensedMatchesByCustomerIds(SummaryType.AvB, 1);

        assertThat(matchDtos, arrayContainingInAnyOrder(MatchDto.builder()
                .matchId(1)
                .playerA("Dan")
                .playerB("Pete")
                .startDate(LocalDateTime.of(2020, 8, 6, 14, 0))
                .summary("Dan vs Pete")
                .build(), MatchDto.builder().matchId(2).playerA("Matt").playerB("Chris").startDate(
                LocalDateTime.of(2020, 8, 6, 16, 0)).summary("Matt vs Chris").build(), MatchDto.builder()
                .matchId(3)
                .playerA("Steve")
                .playerB("John")
                .startDate(LocalDateTime.of(2020, 8, 7, 14, 0))
                .summary("Steve vs John")
                .build(), MatchDto.builder().matchId(4).playerA("Rob").playerB("James").startDate(
                LocalDateTime.of(2020, 8, 7, 16, 0)).summary("Rob vs James").build()));
    }
}