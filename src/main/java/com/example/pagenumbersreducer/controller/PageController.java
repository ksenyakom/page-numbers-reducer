package com.example.pagenumbersreducer.controller;

import com.example.pagenumbersreducer.service.PageService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    private final PageService pageService;
    private final ConversionService conversionService;

    public PageController(PageService pageService, ConversionService conversionService) {
        this.pageService = pageService;
        this.conversionService = conversionService;
    }

    @GetMapping("/reducedPageNumbers")
    public ReducedPagesView reducePages(@RequestParam(value = "rowPageNumbers") List<Integer> pages) {
        ReducedPages reducedPages = new ReducedPages();
        reducedPages.setReduced(pageService.reducePageNumber(pages));
        reducedPages.setOriginal(pages);

        return conversionService.convert(reducedPages, ReducedPagesView.class);
    }
}
