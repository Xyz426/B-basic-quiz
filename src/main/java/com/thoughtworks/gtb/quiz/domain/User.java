package com.thoughtworks.gtb.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    @NotNull(message = "用户名不为空")
    @Size(min = 1, max = 16, message = "用户名不合法")
    private String name;

    @NotNull(message = "年龄不为空")
    @Min(value = 17, message = "年龄必须大于16岁")
    private Long age;

    @NotNull(message = "链接不为空")
    @Size(min = 1, max = 64, message = "链接长度不合法")
    private String avatar;

    @Size(min = 0, max = 128, message = "个人介绍长度不合法")
    private String description;

    @JsonIgnore
    private List<Education> educationList;
}
