package com.img.controller.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchDto {
    private long matchId;
    private LocalDateTime startDate;
    private String playerA;
    private String playerB;
    private String summary;
}
