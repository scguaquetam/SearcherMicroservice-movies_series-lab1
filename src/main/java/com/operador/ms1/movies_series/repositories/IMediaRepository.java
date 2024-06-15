package com.operador.ms1.movies_series.repositories;

import com.operador.ms1.movies_series.models.db.MediaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaRepository extends JpaRepository<MediaModel, Long> {
}
