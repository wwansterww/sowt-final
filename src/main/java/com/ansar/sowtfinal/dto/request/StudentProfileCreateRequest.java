package com.ansar.sowtfinal.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentProfileCreateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String studentNumber;

    @NotBlank
    private String major;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer studyYear;
}
