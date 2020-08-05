package com.img.controller;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.img.controller.dto.MatchDto;
import com.img.controller.dto.SummaryType;
import com.img.service.MatchesService;

@RestController
@RequestMapping(path = "api/matches", produces = "application/json")
public class MatchesApi {

    private final MatchesService matchesService;

    @Autowired
    public MatchesApi(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    /**
     * Return all matches which are associated to the customer.
     *
     * @param customerId  customer id
     * @param summaryType summary type (AvB|AvBTime) it is an optional value default value is AvB
     */
    @GetMapping(path = "{customerId}")
    public ResponseEntity<MatchDto[]> licensedMatchesByCustomerId(@PathVariable("customerId") long customerId,
            @RequestParam(value = "summaryType", defaultValue = "AvB") SummaryType summaryType) {
        return ResponseEntity.ok(matchesService.licensedMatchesByCustomerIds(summaryType, customerId));
    }

    /**
     * Return all matches which are associated to the customers.
     *
     * @param customerIds comma separated customer ids
     * @param summaryType summary type (AvB|AvBTime) it is an optional value default value is AvB
     */
    @GetMapping
    public ResponseEntity<MatchDto[]> licensedMatchesByCustomerIds(@RequestParam("customerIds") String customerIds,
            @RequestParam(value = "summaryType", defaultValue = "AvB") SummaryType summaryType) {
        long[] custIds = Stream.of(customerIds.split(",")).mapToLong(Long::valueOf).toArray();

        return ResponseEntity.ok(matchesService.licensedMatchesByCustomerIds(summaryType, custIds));
    }

}
