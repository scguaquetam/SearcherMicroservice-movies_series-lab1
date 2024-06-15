package com.operador.ms1.movies_series.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MoviesSeries {

    private Long id;
    private String type;
    private String title;
    private String poster;
    private String description;
    private String director;
    private Integer releaseYear;
    private String duration;
    private String rating;
    private String trailerID;
}
