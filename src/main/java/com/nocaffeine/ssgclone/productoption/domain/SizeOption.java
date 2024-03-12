package com.nocaffeine.ssgclone.productoption.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SizeOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String size;
}
