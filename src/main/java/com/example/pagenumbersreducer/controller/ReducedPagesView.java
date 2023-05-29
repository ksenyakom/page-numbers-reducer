package com.example.pagenumbersreducer.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReducedPagesView {

    @Schema(description = "Original page numbers list", requiredMode = Schema.RequiredMode.REQUIRED)
    private String original;

    @Schema(description = "Reduced page numbers list", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reduced;

    @Schema(description = "Book id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bookId;
}
