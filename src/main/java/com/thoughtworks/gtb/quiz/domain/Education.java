package com.thoughtworks.gtb.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class Education {
    @NotNull
    @Size(min = 1, max = 4096, message = "description不合法")
    private String description;

    @NotNull
    private long year;

    @NotNull(message = "title不为空")
    @Size(min = 1, max = 256, message = "title不合法")
    private String title;

    private long userId;
}
