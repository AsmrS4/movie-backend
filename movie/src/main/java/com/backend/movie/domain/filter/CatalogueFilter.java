package com.backend.movie.domain.filter;

import com.backend.movie.domain.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogueFilter {
    private String search;
    private String director;
    private String country;
    private int minYear;
    private int maxYear;
    private int minAgeLimit;
    private int maxAgeLimit;
    private SortType sortBy;
}
