package com.backend.movie.domain.filter;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogueFilter {
    @Nullable
    private String search;
    @Nullable
    private Integer minYear;
    @Nullable
    private Integer maxYear;
    @Nullable
    private Integer minAgeLimit;
    @Nullable
    private Integer maxAgeLimit;
}
