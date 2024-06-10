package com.buscador.ms2.movies_series.data;

import java.util.List;
import com.buscador.ms2.movies_series.model.pojo.MediaElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface MediaElementJpaRepository extends JpaRepository<MediaElement, Long>, JpaSpecificationExecutor<MediaElement> {

    List<MediaElement> findByType(String type);

    List<MediaElement> findByTitle(String title);

    List<MediaElement> findByDirector(String director);

    List<MediaElement> findByReleaseYear(Integer releaseYear);

    List<MediaElement> findByTypeAndTitle(String type, String title);

    List<MediaElement> findByTypeAndDirector(String type, String director);

    List<MediaElement> findByTypeAndReleaseYear(String type, Integer releaseYear);

    List<MediaElement> findByTypeAndTitleAndDirectorAndReleaseYear(String type, String title, String director, Integer releaseYear);
}
