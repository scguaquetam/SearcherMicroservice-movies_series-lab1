package com.operador.ms1.movies_series.utils;

import com.operador.ms1.movies_series.models.db.MediaModel;

public class MediaElementsMapper {

    public static MediaModel toUpdate(MediaModel request, MediaModel mediaElement) {

        if (null != request.getMediaElements()) {
            mediaElement.setMediaElements(request.getMediaElements());
        }

        return mediaElement;
    }
}
