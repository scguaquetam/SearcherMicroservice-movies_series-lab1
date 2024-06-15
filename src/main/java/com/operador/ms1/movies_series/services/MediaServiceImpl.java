package com.operador.ms1.movies_series.services;

import com.operador.ms1.movies_series.facade.MediaFacade;
import com.operador.ms1.movies_series.models.MoviesSeries;
import com.operador.ms1.movies_series.models.db.MediaModel;
import com.operador.ms1.movies_series.models.request.OperatorRequest;
import com.operador.ms1.movies_series.repositories.IMediaRepository;
import com.operador.ms1.movies_series.utils.MediaElementsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl {

    @Autowired
    private MediaFacade mediaFacade;

    @Autowired
    IMediaRepository mediaRepository;

    public ArrayList<MediaModel> getMediaElements() {
        return (ArrayList<MediaModel>) mediaRepository.findAll();
    }

    public MediaModel saveMediaElement(OperatorRequest request) {

        List<MoviesSeries> mediaElements = request.getMediaElements().stream().map(mediaFacade::getMedia).filter(Objects::nonNull).toList();

        MediaModel mediaModel = MediaModel.builder().mediaElements(mediaElements.stream().map(MoviesSeries::getId).collect(Collectors.toList())).build();
        mediaRepository.save(mediaModel);

        return mediaModel;
    }

    public Optional<MediaModel> getMediaElementById(Long id) {
        return mediaRepository.findById(id);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public MediaModel updateMediaElementById(MediaModel request, Long id) {
        MediaModel mediaElement = mediaRepository.findById(id).get();

        return mediaRepository.save(MediaElementsMapper.toUpdate(request, mediaElement));
    }

    public Boolean deleteMediaElementById(Long id) {
        try {
            mediaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
