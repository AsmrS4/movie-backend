package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(
        name = "favourites",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "movieId"})}
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId", referencedColumnName = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MovieEntity movie;
}
