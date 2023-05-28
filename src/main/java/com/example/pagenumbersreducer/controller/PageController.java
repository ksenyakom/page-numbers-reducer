package com.example.pagenumbersreducer.controller;

import com.example.pagenumbersreducer.service.PageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
public class PageController {

    private final PageService pageService;
    private final ConversionService conversionService;

    public PageController(PageService pageService, ConversionService conversionService) {
        this.pageService = pageService;
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
            @RequestParam(value = "rowPageNumbers")
            List<Integer> pages) {
        ReducedPages reducedPages = new ReducedPages();
        reducedPages.setReduced(pageService.reducePageNumber(pages));
        reducedPages.setOriginal(pages);

        return conversionService.convert(reducedPages, ReducedPagesView.class);
    }
}
