package com.buscador.ms2.movies_series.service;

import java.util.List;

import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import com.buscador.ms2.movies_series.model.pojo.MediaElementDto;
import com.buscador.ms2.movies_series.model.request.CreateMediaElementRequest;

public interface MediaElementService {
    List<MediaElement> getMediaElements(String type, String title, String director, Integer releaseYear);
    MediaElement getMediaElement(String mediaElementId);
    Boolean removeMediaElement(String mediaElementId);
    MediaElement createMediaElement(CreateMediaElementRequest mediaElement);
    MediaElement updateMediaElement(String mediaElementId, String updateRequest);
    MediaElement updateMediaElement(String mediaElementId, MediaElementDto updateRequest);
}