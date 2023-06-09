package com.example.pagenumbersreducer.controller;

import com.example.pagenumbersreducer.model.Pages;
import com.example.pagenumbersreducer.service.BookService;
import com.example.pagenumbersreducer.service.PageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PageController {

    private final PageService pageService;
    private final BookService bookService;
    private final ConversionService conversionService;

    public PageController(PageService pageService, BookService bookService, ConversionService conversionService) {
        this.pageService = pageService;
        this.bookService = bookService;
        this.conversionService = conversionService;
    }

    @Operation(description = "Use for reducing pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReducedPagesView.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/reducedPageNumbers")
    public ReducedPagesView reducePages(
            @Parameter(description = "List of page numbers, which need to be reduced")
            @RequestParam(value = "rawPageNumbers")
            List<String> originalPages,
            @Parameter(description = "Book id")
            @RequestParam(value = "bookId")
            Long bookId) {

        var intPages = convertPages(originalPages);

        bookService.validateBookMaxPage(bookId, intPages);

        Pages pages = new Pages();
        pages.setReduced(pageService.reducePageNumber(intPages));
        pages.setOriginal(intPages);
        pages.setBookId(bookId);

        return conversionService.convert(pages, ReducedPagesView.class);
    }

    private List<Integer> convertPages(List<String> originalPages) {
        return originalPages.stream()
                .map(stringNumber -> {
                    try {
                        return Integer.valueOf(stringNumber);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(String.format("%s is not valid page number", stringNumber));
                    }
                })
                .collect(Collectors.toList());
    }
}
