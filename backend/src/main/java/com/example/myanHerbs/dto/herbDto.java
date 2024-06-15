package com.example.myanHerbs.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class herbDto {

    @NotNull(message = "name is require")
    @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
    private String name;

    @NotNull(message = "backround is require")
    @Size(min = 1,max = 500,message = "background must be between 1 and 500 characters")
    private String background;

    @NotNull(message = "detail is require")
    @Size(min = 1,max = 500,message = "detail must be between 1 and 500 characters")
    private String detail;

    @NotNull
    private int code;
}
