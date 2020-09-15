package com.thoughtworks.gtb.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Education {
    private String description;
    private long year;
    private String title;
    private long userId;
}
