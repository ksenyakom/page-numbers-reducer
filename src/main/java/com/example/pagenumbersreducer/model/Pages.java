package com.example.pagenumbersreducer.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Pages {

    private Long bookId;

    private List<Integer> original;

    private Set<List<Integer>> reduced;
}
