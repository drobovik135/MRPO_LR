package ru.mrpo_lr.services.mediaCategory;

import ru.mrpo_lr.models.MediaCategoryResponse;

import java.util.List;

public interface MediaCategoryService {
    List<MediaCategoryResponse> getMediaCategories();
    MediaCategoryResponse getMediaCategory(Long id);
    MediaCategoryResponse addMediaCategory(MediaCategoryResponse mediaCategory);
    MediaCategoryResponse updateMediaCategory(Long id, MediaCategoryResponse mediaCategory);
    MediaCategoryResponse deleteMediaCategory(Long id);

}
