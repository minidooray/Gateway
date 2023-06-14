package com.nhnacademy.minidooray.gateway.gateway.domain.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRegister {
    private Long projectId;
    private String milestonetitle;
    private String dueDate;
}
