package com.example.pagenumbersreducer.controller;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ReducedPages {

    private List<Integer> original;
    private Set<List<Integer>> reduced;
}
