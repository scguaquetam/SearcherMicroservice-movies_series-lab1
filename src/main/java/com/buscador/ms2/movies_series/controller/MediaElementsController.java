package com.buscador.ms2.movies_series.controller;

import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import com.buscador.ms2.movies_series.service.MediaElementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Media Elements Controller", description = "Microservice in charge of expose the CRUD operations over the Movies and Series stored in the memory database (H2 for now).")
public class MediaElementsController {
    private final MediaElementsService service;

    @GetMapping("/media_elements")
    @Operation(
            operationId = "Get media elements",
            description = "Read operation",
            summary = "Returns a list of all the media elements stored in the database.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MediaElement.class)))
    public ResponseEntity<List<MediaElement>> getMediaElements(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "type", description = "Type of the media element. Can be 'movie' or 'series'", example = "movie", required = false)
            @RequestParam(required = false) String type,
            @Parameter(name = "title", description = "Title of the series/movie. It does not have to be exact", example = "The Lord of the Rings", required = false)
            @RequestParam(required = false) String title,
            @Parameter(name = "poster", description = "Poster of the series/movie. It does not have to be exact", example = "https://www.google.com", required = false)
            @RequestParam(required = false) String poster,
            @Parameter(name = "description", description = "Description of the series/movie. It does not have to be exact", example = "A great movie", required = false)
            @RequestParam(required = false) String description,
            @Parameter(name = "director", description = "Director of the series/movie. It does not have to be exact", example = "Peter Jackson", required = false)
            @RequestParam(required = false) String director,
            @Parameter(name = "year", description = "Year of the series/movie. It does not have to be exact", example = "2001", required = false)
            @RequestParam(required = false) Integer year,
            @Parameter(name = "duration", description = "Duration of the series/movie. It does not have to be exact", example = "2h 58m", required = false)
            @RequestParam(required = false) String duration,
            @Parameter(name = "rating", description = "Rating of the series/movie. It does not have to be exact", example = "8.8", required = false)
            @RequestParam(required = false) String rating,
            @Parameter(name = "trailerID", description = "Trailer ID of the series/movie. It does not have to be exact", example = "dQw4w9WgXcQ", required = false)
            @RequestParam(required = false) String trailerID) {
        List<MediaElement> mediaElements = service.getMediaElements(type, title, poster, description, director, year, duration, rating, trailerID);
        if (mediaElements != null) {
            return ResponseEntity.ok(mediaElements);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
