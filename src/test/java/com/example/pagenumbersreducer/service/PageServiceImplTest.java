package com.example.pagenumbersreducer.service;

import com.example.pagenumbersreducer.exception.PageException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PageServiceImplTest {

    private static PageService pageService;

    @BeforeAll
    static void setUp() {
        pageService = new PageServiceImpl();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void reducePageNumber_emptyPages_throwsException(List<Integer> pages) {
        assertThrows(PageException.class, () -> pageService.reducePageNumber(pages));
    }

    private static Stream<Arguments> notValidValues() {
        return Stream.of(
                Arguments.of(newArrayList(7, -1)),
                Arguments.of(newArrayList(1, 0, 5)),
                Arguments.of(newArrayList(null, 100, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("notValidValues")
    void reducePageNumber_notValidValues_throwsException(List<Integer> pages) {
        assertThrows(PageException.class, () -> pageService.reducePageNumber(pages));
    }

    private static Stream<Arguments> validValues() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 32, 32, 2),
                        newLinkedHashSet(List.of(1, 2), List.of(32))
                ),
                Arguments.of(List.of(1, 3, 32, 5, 11, 7, 6, 19, 2, 21, 4, 8, 22, 23),
                        newLinkedHashSet(List.of(1, 8), List.of(11), List.of(19), List.of(21, 23), List.of(32))
                ),
                Arguments.of(List.of(1, 100500, 2),
                        newLinkedHashSet(List.of(1, 2), List.of(100500))
                ),
                Arguments.of(List.of(999),
                        newLinkedHashSet(List.of(999))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validValues")
    void reducePageNumber_validValues_returnRightResult(List<Integer> pages, Set<List<Integer>> result) {
        var reducedPages = pageService.reducePageNumber(pages);

        assertEquals(result, reducedPages);
    }

}