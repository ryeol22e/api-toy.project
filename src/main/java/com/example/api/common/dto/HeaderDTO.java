package com.example.api.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HeaderDTO {
    private String id;
    private String name;
    private String path;
}
