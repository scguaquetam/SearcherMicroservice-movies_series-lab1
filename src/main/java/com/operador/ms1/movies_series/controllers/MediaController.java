package com.operador.ms1.movies_series.controllers;

import com.operador.ms1.movies_series.models.db.MediaModel;
import com.operador.ms1.movies_series.models.request.OperatorRequest;
import com.operador.ms1.movies_series.services.MediaServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/movies-series")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaServiceImpl;

    @GetMapping
    public ArrayList<MediaModel> getMediaElements() {
        return this.mediaServiceImpl.getMediaElements();
    }

    @PostMapping
    public MediaModel saveMediaElement(@RequestBody @Valid OperatorRequest request) {
        return this.mediaServiceImpl.saveMediaElement(request);
    }

    @GetMapping(path = "/{id}")
    public Optional<MediaModel> getMediaElementById(@PathVariable("id") Long id) {
        return this.mediaServiceImpl.getMediaElementById(id);
    }

    @PutMapping(path = "/{id}")
    public MediaModel updateMediaElementById(@RequestBody MediaModel request, @PathVariable("id") Long id) {
        return this.mediaServiceImpl.updateMediaElementById(request, id);
    }

    @PatchMapping(path = "/{id}")
    public MediaModel patchMediaElementById(@RequestBody MediaModel request, @PathVariable("id") Long id) {
        return this.mediaServiceImpl.updateMediaElementById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteMediaElementById(@PathVariable("id") Long id) {
        boolean ok = this.mediaServiceImpl.deleteMediaElementById(id);
        if(ok) {
            return "User with id ".concat(id.toString()).concat(" has been deleted.");
        } else {
            return "Error deleting user with id ".concat(id.toString()).concat(".");
        }
    }
}
