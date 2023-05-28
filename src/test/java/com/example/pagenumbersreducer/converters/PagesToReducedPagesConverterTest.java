package com.example.pagenumbersreducer.converters;

import com.example.pagenumbersreducer.controller.ReducedPagesView;
import com.example.pagenumbersreducer.model.Pages;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PagesToReducedPagesConverterTest {

    private final PagesToReducedPagesConverter converter = new PagesToReducedPagesConverter();

    @Test
    void convert() {
        Pages pages = new Pages();
        pages.setOriginal(List.of(1, 2, 3, 5, 7, 8));
        pages.setReduced(newLinkedHashSet(List.of(1, 3), List.of(5), List.of(7, 8)));

        ReducedPagesView reducedPagesView = converter.convert(pages);

        assertNotNull(reducedPagesView);
        assertEquals("1,2,3,5,7,8", reducedPagesView.getOriginal());
        assertEquals("1-3,5,7-8", reducedPagesView.getReduced());
    }

}