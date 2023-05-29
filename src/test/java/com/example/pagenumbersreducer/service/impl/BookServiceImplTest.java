package com.example.pagenumbersreducer.service.impl;

import com.example.pagenumbersreducer.exception.PageValidationException;
import com.example.pagenumbersreducer.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookServiceImplTest {

    private static BookService bookService;

    @BeforeAll
    static void setUp() {
        bookService = new BookServiceImpl();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validateBookMaxPage_pagesIsEmpty_throwException(List<Integer> pages) {
        assertThrows(PageValidationException.class, () -> bookService.validateBookMaxPage(1L, pages));
    }

    @Test
    void validateBookMaxPage_bookIdIsNull_throwException() {
        assertThrows(NullPointerException.class, () -> bookService.validateBookMaxPage(null, List.of(1, 2, 3)));
    }

    @Test
    void validateBookMaxPage_validArguments_returnTrue() {
        assertTrue( bookService.validateBookMaxPage(11L, List.of(1, 2, 3)));
    }
}