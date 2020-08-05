package com.img;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.img.controller.dto.MatchDto;
import com.img.controller.dto.SummaryType;
import com.img.service.MatchesService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
class SpringBootRestDocsApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchesService matchesService;

    @Test
    public void licensedMatchesByCustomerIdTest() throws Exception {
        MatchDto[] t = {matchDto(1), matchDto(2)};
        Mockito.when(matchesService.licensedMatchesByCustomerIds(SummaryType.AvB, 1)).thenReturn(t);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/matches/1?summaryType=AvB"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].matchId", is(1)))
                .andExpect(jsonPath("$.[0].playerA", is("playerA1")))
                .andExpect(jsonPath("$.[0].playerB", is("playerB1")))
                .andExpect(jsonPath("$.[0].summary", is("summary1")))
                .andExpect(jsonPath("$.[1].matchId", is(2)))
                .andExpect(jsonPath("$.[1].playerA", is("playerA2")))
                .andExpect(jsonPath("$.[1].playerB", is("playerB2")))
                .andExpect(jsonPath("$.[1].summary", is("summary2")))
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andDo(document("matches/{customerId}", requestParameters(parameterWithName("summaryType").description(
                        "The summery type. Supported values: AvB|AvBTime").optional()),
                        responseFields(fieldWithPath("[].matchId").description("The match identifier"),
                                fieldWithPath("[].startDate").description("The match start date time"),
                                fieldWithPath("[].playerA").description("Player A of the match"),
                                fieldWithPath("[].playerB").description("Player B of the match"),
                                fieldWithPath("[].summary").description("Match summary"))));
    }

    @Test
    public void licensedMatchesByCustomerIdsTest() throws Exception {
        MatchDto[] t = {matchDto(1), matchDto(2)};
        Mockito.when(matchesService.licensedMatchesByCustomerIds(SummaryType.AvBTime, 1, 2)).thenReturn(t);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/matches?customerIds=1,2&summaryType=AvBTime").accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].matchId", is(1)))
                .andExpect(jsonPath("$.[0].playerA", is("playerA1")))
                .andExpect(jsonPath("$.[0].playerB", is("playerB1")))
                .andExpect(jsonPath("$.[0].summary", is("summary1")))
                .andExpect(jsonPath("$.[1].matchId", is(2)))
                .andExpect(jsonPath("$.[1].playerA", is("playerA2")))
                .andExpect(jsonPath("$.[1].playerB", is("playerB2")))
                .andExpect(jsonPath("$.[1].summary", is("summary2")))
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andDo(document("matches",
                        requestParameters(parameterWithName("customerIds").description("Comma separated customer ids"),
                                parameterWithName("summaryType").description(
                                        "The summery type. Supported values: AvB|AvBTime").optional()),
                        responseFields(fieldWithPath("[].matchId").description("The match identifier"),
                                fieldWithPath("[].startDate").description("The match start date time"),
                                fieldWithPath("[].playerA").description("Player A of the match"),
                                fieldWithPath("[].playerB").description("Player B of the match"),
                                fieldWithPath("[].summary").description("Match summary"))));
    }

    private MatchDto matchDto(int id) {
        return MatchDto.builder().matchId(id).startDate(LocalDateTime.now()).playerA("playerA" + id).playerB(
                "playerB" + id).summary("summary" + id).build();
    }
}
