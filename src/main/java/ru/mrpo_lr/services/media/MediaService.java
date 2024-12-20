package ru.mrpo_lr.services.media;


import ru.mrpo_lr.models.MediaResponse;

import java.util.List;

public interface MediaService {
    List<MediaResponse> getMedias();
    MediaResponse getMediaById(Long id);
    MediaResponse addMedia(Long categoryId, MediaResponse media);
    MediaResponse updateMedia(Long id, MediaResponse media);
    List<MediaResponse> mediasByCategory(Long categoryId);
    void fillDatabase();
    void deleteMedia(Long id);
}
