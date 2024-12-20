package ru.mrpo_lr.services.mediaCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrpo_lr.entity.Media;
import ru.mrpo_lr.entity.MediaCategory;
import ru.mrpo_lr.models.MediaCategoryResponse;
import ru.mrpo_lr.repositories.MediaCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaCategoryServiceImpl implements MediaCategoryService {
    private final MediaCategoryRepository mediaCategoryRepository;

    private MediaCategoryResponse buildResponse(MediaCategory mediaCategory) {
        return new MediaCategoryResponse()
                .setName(mediaCategory.getName())
                .setInfo(mediaCategory.getInfo())
                .setId(mediaCategory.getId())
                .setMediaIds(mediaCategory.getMedias().stream().map(Media::getId).collect(Collectors.toList()));
    }

    @Autowired
    public MediaCategoryServiceImpl(MediaCategoryRepository mediaCategoryRepository) {
        this.mediaCategoryRepository = mediaCategoryRepository;
    }


    @Override
    public List<MediaCategoryResponse> getMediaCategories() {
        return mediaCategoryRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }


    @Override
    public MediaCategoryResponse getMediaCategory(Long id) {
        if(!mediaCategoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media category with id %s not found", id));
        }

        return buildResponse(mediaCategoryRepository.getReferenceById(id));
    }


    @Override
    public MediaCategoryResponse addMediaCategory(MediaCategoryResponse mediaCategoryResponse) {
        if(mediaCategoryResponse.getName() == null || mediaCategoryResponse.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be null");
        }

        if(mediaCategoryRepository.existsByName(mediaCategoryResponse.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists");
        }

        MediaCategory mediaCategory = new MediaCategory();
        mediaCategory.setName(mediaCategoryResponse.getName());
        mediaCategory.setInfo(mediaCategoryResponse.getInfo());

        mediaCategoryRepository.save(mediaCategory);
        return buildResponse(mediaCategory);
    }


    @Override
    public MediaCategoryResponse updateMediaCategory(Long id, MediaCategoryResponse mediaCategoryResponse) {
        if(mediaCategoryResponse.getName() == null || mediaCategoryResponse.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be null");
        }

        if(!mediaCategoryRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media category with id %s not found", id));
        }

        MediaCategory mediaCategory = mediaCategoryRepository.getReferenceById(id);

        if(mediaCategoryRepository.existsByName(mediaCategoryResponse.getName())) {
            if(mediaCategoryRepository.findByName(mediaCategoryResponse.getName()) != mediaCategory) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists");
            }
        }

        mediaCategory.setName(mediaCategoryResponse.getName());
        mediaCategory.setInfo(mediaCategoryResponse.getInfo());

        mediaCategoryRepository.save(mediaCategory);
        return buildResponse(mediaCategory);
    }


    @Override
    public void deleteMediaCategory(Long id) {
        if(!mediaCategoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media category with id %s not found", id));
        }

        mediaCategoryRepository.deleteById(id);
    }
}
