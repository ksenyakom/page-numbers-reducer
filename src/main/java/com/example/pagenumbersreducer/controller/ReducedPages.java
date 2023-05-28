package com.example.pagenumbersreducer.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ReducedPages {

    @Schema(required = true)
    private List<Integer> original;
    @Schema(required = true)
    private Set<List<Integer>> reduced;
}
