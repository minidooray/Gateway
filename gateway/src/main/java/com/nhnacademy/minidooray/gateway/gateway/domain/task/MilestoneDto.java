package com.nhnacademy.minidooray.gateway.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneDto {
    private Long milestoneId;
    private String milestoneContent;
    private LocalDate milestoneStartAt;
    private LocalDate milestoneEndAt;
}
