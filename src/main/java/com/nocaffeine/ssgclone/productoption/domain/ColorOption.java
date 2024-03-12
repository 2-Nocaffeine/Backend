package com.nocaffeine.ssgclone.productoption.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ColorOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String color;
}
