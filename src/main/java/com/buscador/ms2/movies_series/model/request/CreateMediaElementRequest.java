package com.buscador.ms2.movies_series.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMediaElementRequest {
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
