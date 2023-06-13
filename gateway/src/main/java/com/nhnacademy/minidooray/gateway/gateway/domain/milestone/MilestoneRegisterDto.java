package com.nhnacademy.minidooray.gateway.gateway.domain.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MilestoneRegisterDto {
    private String milestoneContent;
    private LocalDate milestoneStartAt;
    private LocalDate milestoneEndAt;
}
