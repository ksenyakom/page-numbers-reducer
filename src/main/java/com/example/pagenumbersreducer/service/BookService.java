package com.example.pagenumbersreducer.service;

import java.util.List;

public interface BookService {

    boolean validateBookMaxPage(Long bookId, List<Integer> pages);
}
