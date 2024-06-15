package com.buscador.ms2.movies_series.service;
import java.util.List;

import com.buscador.ms2.movies_series.data.MediaElementRepository;
import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import com.buscador.ms2.movies_series.model.pojo.MediaElementDto;
import com.buscador.ms2.movies_series.model.request.CreateMediaElementRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaElementsServiceImpl implements MediaElementService {

    private final MediaElementRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public List<MediaElement> getMediaElements(String type, String title, String director, Integer releaseYear) {
        log.info("Request received for media elements with type: {}, title: {}, director: {}, releaseYear: {}", type, title, director, releaseYear);
        if (StringUtils.hasLength(type) || StringUtils.hasLength(title) || StringUtils.hasLength(director) || releaseYear != null ) {
            log.info("Searching media elements with type: {}, title: {}, director: {}, releaseYear: {}", type, title, director, releaseYear);
            return repository.search(type, title, director, releaseYear);
        }
        List<MediaElement> mediaElements = repository.getMediaElements();
        log.info("Returning {} media elements", mediaElements.size());
        return mediaElements.isEmpty() ? null : mediaElements;
    }
    @Override
    public MediaElement getMediaElement(String mediaElementId) {
        return repository.getById(Long.valueOf(mediaElementId));
    }
    @Override
    public Boolean removeMediaElement(String mediaElementId) {
        MediaElement mediaElement = repository.getById(Long.valueOf(mediaElementId));
        if (mediaElement != null) {
            repository.delete(mediaElement);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    @Override
    public MediaElement createMediaElement(CreateMediaElementRequest mediaElement) {
        if (mediaElement != null
                && StringUtils.hasLength(mediaElement.getType())
                && StringUtils.hasLength(mediaElement.getTitle())
                && StringUtils.hasLength(mediaElement.getPoster())
                && StringUtils.hasLength(mediaElement.getDescription())
                && StringUtils.hasLength(mediaElement.getDirector())
                && StringUtils.hasLength(mediaElement.getDuration())
                && StringUtils.hasLength(mediaElement.getRating())
                && StringUtils.hasLength(mediaElement.getTrailerID())
                && mediaElement.getReleaseYear() != null) {
            MediaElement newMediaElement = MediaElement.builder()
                    .type(mediaElement.getType())
                    .title(mediaElement.getTitle())
                    .poster(mediaElement.getPoster())
                    .description(mediaElement.getDescription())
                    .director(mediaElement.getDirector())
                    .releaseYear(mediaElement.getReleaseYear())
                    .duration(mediaElement.getDuration())
                    .rating(mediaElement.getRating())
                    .trailerID(mediaElement.getTrailerID())
                    .build();
            return repository.save(newMediaElement);
        } else {
            return null;
        }
    }
    @Override
    public MediaElement updateMediaElement(String mediaElementId, String updateRequest) {
        MediaElement mediaElement = repository.getById(Long.valueOf(mediaElementId));
        if (mediaElement != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updateRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(mediaElement)));
                MediaElement updatedMediaElement = objectMapper.treeToValue(target, MediaElement.class);
                repository.save(updatedMediaElement);
                return updatedMediaElement;
            } catch (Exception e) {
                log.error("Error updating media element {}", mediaElementId ,e);
                return null;
            }
        } else {
            return null;
        }
    }
    @Override
    public MediaElement updateMediaElement(String mediaElementId, MediaElementDto updateRequest) {
        MediaElement mediaElement = repository.getById(Long.valueOf(mediaElementId));
        if (mediaElement != null) {
            mediaElement.update(updateRequest);
            repository.save(mediaElement);
            return mediaElement;
        } else {
            return null;
        }
    }
}
