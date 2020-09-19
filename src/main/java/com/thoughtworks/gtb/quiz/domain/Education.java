package com.thoughtworks.gtb.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.gtb.quiz.serializer.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotNull
    @Size(min = 1, max = 4096, message = "description不合法")
    private String description;

    @NotNull
    private long year;

    @NotNull(message = "title不为空")
    @Size(min = 1, max = 256, message = "title不合法")
    private String title;

    @ManyToOne
    @JsonProperty("user_id")
    @JsonSerialize(using = UserSerializer.class)
    private User user;
}
