package com.example.pagenumbersreducer.service.impl;

import com.example.pagenumbersreducer.exception.PageValidationException;
import com.example.pagenumbersreducer.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class BookServiceImpl implements BookService {
    @Override
    public boolean validateBookMaxPage(Long bookId, List<Integer> pages) {
        Objects.requireNonNull(bookId, "Book id must not be null");
        if (CollectionUtils.isEmpty(pages)) {
            throw new PageValidationException(String.format("Pages must not be null or empty, found %s", pages));
        }

        // call repository or another service

        return true;
    }
}
