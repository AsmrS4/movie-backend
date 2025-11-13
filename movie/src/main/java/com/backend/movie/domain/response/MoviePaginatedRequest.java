package com.backend.movie.domain.response;

import com.backend.movie.domain.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviePaginatedRequest {
    List<Movie> movies;
    Pagination pagination;
}
