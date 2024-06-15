package com.buscador.ms2.movies_series.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mediaElements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MediaElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "poster")
    private String poster;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "duration")
    private String duration;

    @Column(name = "rating")
    private String rating;

    @Column(name = "trailerID")
    private String trailerID;

    public void update(MediaElementDto mediaElementDto) {
        this.type = mediaElementDto.getType();
        this.title = mediaElementDto.getTitle();
        this.poster = mediaElementDto.getPoster();
        this.description = mediaElementDto.getDescription();
        this.director = mediaElementDto.getDirector();
        this.releaseYear = mediaElementDto.getReleaseYear();
        this.duration = mediaElementDto.getDuration();
        this.rating = mediaElementDto.getRating();
        this.trailerID = mediaElementDto.getTrailerID();
    }
}
