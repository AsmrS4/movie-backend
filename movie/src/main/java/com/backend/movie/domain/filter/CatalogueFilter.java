package com.backend.movie.domain.filter;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

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
    @Nullable
    private List<UUID> genres;
}
