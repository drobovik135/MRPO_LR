package ru.mrpo_lr.services.entryMedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrpo_lr.entity.EntryMedia;
import ru.mrpo_lr.entity.Media;
import ru.mrpo_lr.entity.MyListTable;
import ru.mrpo_lr.models.EntryMediaResponse;
import ru.mrpo_lr.repositories.EntryMediaRepository;
import ru.mrpo_lr.repositories.MediaRepository;
import ru.mrpo_lr.repositories.MyListTableRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryMediaServiceImpl implements EntryMediaService {
    private final EntryMediaRepository entryMediaRepository;
    private final MediaRepository mediaRepository;
    private final MyListTableRepository myListTableRepository;

    private EntryMediaResponse buildResponse(EntryMedia entryMedia) {
        return new EntryMediaResponse()
                .setId(entryMedia.getId())
                .setMediaId(entryMedia.getMedia().getId())
                .setTableId(entryMedia.getTable().getId())
                .setMediaRate(entryMedia.getMediaRate())
                .setMediaReview(entryMedia.getMediaReview());
    }


    @Autowired
    public EntryMediaServiceImpl(EntryMediaRepository entryMediaRepository, MediaRepository mediaRepository, MyListTableRepository myListTableRepository) {
        this.entryMediaRepository = entryMediaRepository;
        this.mediaRepository = mediaRepository;
        this.myListTableRepository = myListTableRepository;
    }

    @Override
    public List<EntryMediaResponse> getAllEntryMedia() {
        return entryMediaRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public EntryMediaResponse getEntryMedia(Long id) {
        if(!entryMediaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("EntryMedia with id %s not found", id));
        }

        return buildResponse(entryMediaRepository.getReferenceById(id));
    }

    @Override
    public EntryMediaResponse addEntryMedia(Long tableId, EntryMediaResponse entryMediaResponse) {
        if(!myListTableRepository.existsById(tableId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("MyListTable with id %s not found", tableId));
        }
        if(!mediaRepository.existsById(entryMediaResponse.getMediaId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media with id %s not found", entryMediaResponse.getMediaId()));
        }

        Media media = mediaRepository.getReferenceById(entryMediaResponse.getMediaId());
        MyListTable myListTable = myListTableRepository.getReferenceById(tableId);

        EntryMedia entryMedia = new EntryMedia();
        entryMedia.setMedia(media);
        entryMedia.setTable(myListTable);
        entryMedia.setMediaRate(entryMediaResponse.getMediaRate());
        entryMedia.setMediaReview(entryMediaResponse.getMediaReview());

        myListTableRepository.save(myListTable);
        mediaRepository.save(media);
        entryMediaRepository.save(entryMedia);

        return buildResponse(entryMedia);
    }

    @Override
    public EntryMediaResponse updateEntryMedia(Long id, EntryMediaResponse entryMediaResponse) {
        if(!entryMediaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("EntryMedia with id %s not found", id));
        }
        EntryMedia entryMedia = entryMediaRepository.getReferenceById(id);

        entryMedia.setMediaRate(entryMediaResponse.getMediaRate());
        entryMedia.setMediaReview(entryMediaResponse.getMediaReview());

        entryMediaRepository.save(entryMedia);

        return buildResponse(entryMedia);
    }

    @Override
    public void deleteEntryMedia(Long id) {
        if(!entryMediaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("EntryMedia with id %s not found", id));
        }

        entryMediaRepository.deleteById(id);
    }

    @Override
    public List<EntryMediaResponse> getAllEntryMediaByTableId(Long tableId) {
        if(!myListTableRepository.existsById(tableId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("MyListTable with id %s not found", tableId));
        }

        MyListTable myListTable = myListTableRepository.getReferenceById(tableId);

        return myListTable.getMedias().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public List<EntryMediaResponse> getAllEntryMediaByMediaId(Long mediaId) {
        if(!mediaRepository.existsById(mediaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Media with id %s not found", mediaId));
        }

        Media media = mediaRepository.getReferenceById(mediaId);

        return media.getEntries().stream().map(this::buildResponse).collect(Collectors.toList());
    }
}
