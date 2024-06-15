package com.buscador.ms2.movies_series.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class MediaElementDto {
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