package com.buscador.ms2.movies_series.controller;

import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import com.buscador.ms2.movies_series.model.pojo.MediaElementDto;
import com.buscador.ms2.movies_series.model.request.CreateMediaElementRequest;
import com.buscador.ms2.movies_series.service.MediaElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    private final MediaElementService service;

    @GetMapping("/media-elements")
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
            @Parameter(name = "director", description = "Director of the series/movie. It does not have to be exact", example = "Peter Jackson", required = false)
            @RequestParam(required = false) String director,
            @Parameter(name = "releaseYear", description = "Year of the series/movie. It does not have to be exact", example = "2001", required = false)
            @RequestParam(required = false) Integer releaseYear
    ) {
        log.info("Request received for media elements");
        log.info("Headers: {}", headers);
        log.info("Type: {}", type);
        log.info("Title: {}", title);
        List<MediaElement> mediaElements = service.getMediaElements(type, title, director, releaseYear);
        if (mediaElements != null) {
            return ResponseEntity.ok(mediaElements);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
    @GetMapping("/media-elements/{mediaElementId}")
    @Operation(
            operationId = "Get media element by id",
            description = "Read operation",
            summary = "Returns a media element by its id.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MediaElement.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Media element not found")
    public ResponseEntity<MediaElement> getMediaElement(@PathVariable String mediaElementId) {
        MediaElement mediaElement = service.getMediaElement(mediaElementId);
        if (mediaElement != null) {
            return ResponseEntity.ok(mediaElement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/media-elements/{mediaElementId}")
    @Operation(
            operationId = "Delete media element",
            description = "Write operation",
            summary = "Deletes a media element by its id.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Media element not found")
    public ResponseEntity<Void> deleteMediaElement(@PathVariable String mediaElementId) {
        Boolean removed = service.removeMediaElement(mediaElementId);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/media-elements")
    @Operation(
            operationId = "Create media element",
            description = "Write operation",
            summary = "Creates a new media element.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Media element to be created",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMediaElementRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MediaElement.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Bad request")
    public ResponseEntity<MediaElement> createMediaElement(@RequestBody CreateMediaElementRequest mediaElement) {
        MediaElement newMediaElement = service.createMediaElement(mediaElement);
        if (newMediaElement != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newMediaElement);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/media-elements/{mediaElementId}")
    @Operation(
            operationId = "Update  partially media element",
            description = "Write operation",
            summary = "Updates a media element by its id.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Media element to be updated",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMediaElementRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MediaElement.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Bad request")
    public ResponseEntity<MediaElement> patchMediaElement(@PathVariable String mediaElementId, @RequestBody String patchBody) {
        MediaElement updatedMediaElement = service.updateMediaElement(mediaElementId, patchBody);
        if (updatedMediaElement != null) {
            return ResponseEntity.ok(updatedMediaElement);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/media-elements/{mediaElementId}")
    @Operation(
            operationId = "Update completely media element",
            description = "Write operation",
            summary = "Modifies completely the media element .",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Media element to be updated",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMediaElementRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MediaElement.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Bad request")
    public ResponseEntity<MediaElement> updateMediaElement(@PathVariable String mediaElementId, @RequestBody MediaElementDto body) {
        MediaElement updatedMediaElement = service.updateMediaElement(mediaElementId, body);
        if (updatedMediaElement != null) {
            return ResponseEntity.ok(updatedMediaElement);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
