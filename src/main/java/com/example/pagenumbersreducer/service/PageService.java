package com.example.pagenumbersreducer.service;

import java.util.List;
import java.util.Set;

public interface PageService {

    Set<List<Integer>> reducePageNumber(List<Integer> pages);
}
