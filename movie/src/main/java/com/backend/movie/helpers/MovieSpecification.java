package com.backend.movie.helpers;

import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public static Specification<MovieEntity> withFilter(CatalogueFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(filter.getSearch()!=null && !filter.getSearch().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + filter.getSearch().toLowerCase() + "%"
                ));
            }
            if(filter.getMinYear()!=null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("filmYear"),
                        filter.getMinYear()
                ));
            }
            if(filter.getMaxYear()!=null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("filmYear"),
                        filter.getMaxYear()
                ));
            }
            if(filter.getMinAgeLimit()!=null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("ageLimit"),
                        filter.getMinAgeLimit()
                ));
            }
            if(filter.getMaxAgeLimit()!=null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("ageLimit"),
                        filter.getMaxAgeLimit()
                ));
            }
            if(filter.getGenres()!= null && !filter.getGenres().isEmpty()) {
                Join<MovieEntity, Genre> genreJoin = root.join("genres");
                predicates.add(genreJoin.get("id").in(filter.getGenres()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("filmYear")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
