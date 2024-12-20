package ru.mrpo_lr.services.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrpo_lr.entity.EntryMedia;
import ru.mrpo_lr.entity.Media;
import ru.mrpo_lr.entity.MediaCategory;
import ru.mrpo_lr.models.MediaResponse;
import ru.mrpo_lr.repositories.MediaCategoryRepository;
import ru.mrpo_lr.repositories.MediaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final MediaCategoryRepository mediaCategoryRepository;

    private MediaResponse buildResponse(Media media) {
        return new MediaResponse()
                .setName(media.getName())
                .setInfo(media.getInfo())
                .setCategoryId(media.getCategory().getId())
                .setId(media.getId())
                .setEntriesId(media.getEntries().stream().map(EntryMedia::getId).collect(Collectors.toList()));
    }

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository, MediaCategoryRepository mediaCategoryRepository) {
        this.mediaRepository = mediaRepository;
        this.mediaCategoryRepository = mediaCategoryRepository;
    }


    @Override
    public List<MediaResponse> getMedias() {
        return mediaRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public MediaResponse getMediaById(Long id) {
        if(!mediaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media with id %s not found", id));
        }

        return buildResponse(mediaRepository.getReferenceById(id));
    }

    @Override
    public MediaResponse addMedia(Long categoryId, MediaResponse mediaResponse) {
        if(!mediaCategoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId));
        }

        if(mediaResponse.getName() == null || mediaResponse.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media name cannot be empty");
        }

        if(mediaRepository.existsByName(mediaResponse.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Media name already exists");
        }

        Media media = new Media();
        media.setName(mediaResponse.getName());
        media.setInfo(mediaResponse.getInfo());

        MediaCategory mediaCategory = mediaCategoryRepository.getReferenceById(categoryId);
        media.setCategory(mediaCategory);
        mediaCategory.addMedia(media);

        mediaRepository.save(media);
        mediaCategoryRepository.save(mediaCategory);
        return buildResponse(media);
    }

    @Override
    public MediaResponse updateMedia(Long id, MediaResponse media) {
        return null;
    }

    @Override
    public List<MediaResponse> mediasByCategory(Long categoryId) {
        if(!mediaCategoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId));
        }

        MediaCategory mediaCategory = mediaCategoryRepository.getReferenceById(categoryId);

        return mediaCategory.getMedias().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteMedia(Long id) {
        if(!mediaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media with id %s not found", id));
        }
        mediaRepository.deleteById(id);
    }
}
