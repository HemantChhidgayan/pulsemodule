package com.pulse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BloodRequestDTO {
    @NotBlank
    private Integer userId;
    @NotBlank
    private String patientName;
    @NotBlank
    private String bloodTypeNeeded;
    @NotBlank
    private String urgency;
    @NotBlank
    private String location;
    @NotBlank
    private Integer unitsRequired;
    @NotBlank
    private Integer age;
    public BloodRequestDTO() {}
}
