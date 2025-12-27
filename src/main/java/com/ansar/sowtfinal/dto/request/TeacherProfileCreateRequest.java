package com.ansar.sowtfinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherProfileCreateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String department;

    @NotBlank
    private String position;
}
