package ru.mrpo_lr.services.entryMedia;

import ru.mrpo_lr.models.EntryMediaResponse;
import ru.mrpo_lr.models.FullEntryMediaResponse;

import java.util.List;

public interface EntryMediaService {
    List<EntryMediaResponse> getAllEntryMedia();
    EntryMediaResponse getEntryMedia(Long id);
    EntryMediaResponse addEntryMedia(Long tableId, EntryMediaResponse entryMedia);
    EntryMediaResponse updateEntryMedia(Long id, EntryMediaResponse entryMedia);
    void deleteEntryMedia(Long id);
    List<EntryMediaResponse> getAllEntryMediaByTableId(Long tableId);
    List<EntryMediaResponse> getAllEntryMediaByMediaId(Long mediaId);
    List<FullEntryMediaResponse> getFullEntryMediaByTable(Long tableId);
}
