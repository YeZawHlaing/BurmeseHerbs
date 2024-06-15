package com.example.myanHerbs.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "herb")
public class herb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
