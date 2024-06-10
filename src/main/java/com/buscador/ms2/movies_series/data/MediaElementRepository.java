package com.buscador.ms2.movies_series.data;
import java.util.List;


import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.buscador.ms2.movies_series.data.utils.SearchCriteria;
import com.buscador.ms2.movies_series.data.utils.SearchOperation;
import com.buscador.ms2.movies_series.data.utils.SearchStatement;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MediaElementRepository {
    private final MediaElementJpaRepository repository;

    public List<MediaElement> getMediaElements() {
        return repository.findAll();
    }
    public MediaElement getById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public MediaElement save(MediaElement mediaElement) {
        return repository.save(mediaElement);
    }
    public void delete(MediaElement mediaElement) {
        repository.delete(mediaElement);
    }
    public List<MediaElement> search(String type, String title, String director, Integer releaseYear ) {
        SearchCriteria<MediaElement> spec = new SearchCriteria<>();
        log.info("Searching media elements with type: {}, title: {}, director: {}, releaseYear: {}", type, title, director, releaseYear);
        log.info("spec is {}" , spec);
        if(StringUtils.isNotBlank(type)) {
            log.info("type is {}" , type);
            spec.add(new SearchStatement("type", type, SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(title)) {
            log.info("title is {}" , title);
            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(director)) {
            log.info("director is {}" , director);
            spec.add(new SearchStatement("director", director, SearchOperation.MATCH));
        }
        if(releaseYear != null) {
            log.info("releaseYear is {}" , releaseYear);
            spec.add(new SearchStatement("releaseYear", releaseYear, SearchOperation.EQUAL));
        }
        log.info("spec is {}" , spec);
        log.info("repository.findAll(spec) is {}" , repository.findAll(spec));
        return repository.findAll(spec);
    }
}
