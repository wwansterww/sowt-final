package com.ansar.sowtfinal.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileResponse {
    private Long id;
    private Long userId;
    private String major;
    private String groupName;
    private Integer year;
}
