package com.example.myanHerbs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "herb")
public class herb {

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "background")
    private String background;

    @Column(name = "detail")
    private String detail;

    @Column(name = "code")
    private int code;


}
