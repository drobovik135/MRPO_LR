package ru.mrpo_lr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrpo_lr.models.EntryMediaResponse;
import ru.mrpo_lr.services.entryMedia.EntryMediaService;

@RestController
public class EntryMediaController {
    private final EntryMediaService entryMediaService;

    public EntryMediaController(EntryMediaService entryMediaService) {
        this.entryMediaService = entryMediaService;
    }


    @GetMapping("/entries")
    public ResponseEntity<?> getMedias() {
        return ResponseEntity.ok(entryMediaService.getAllEntryMedia());
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<?> getMediasById(@PathVariable Long id) {
        return ResponseEntity.ok(entryMediaService.getEntryMedia(id));
    }

    @PostMapping("/tables/{tableId}/entries")
    public ResponseEntity<?> addEntryMedia(@PathVariable Long tableId, @RequestBody EntryMediaResponse entryMedia) {
        return ResponseEntity.ok(entryMediaService.addEntryMedia(tableId, entryMedia));
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<?> updateEntryMedia(@PathVariable Long id, @RequestBody EntryMediaResponse entryMedia) {
        return ResponseEntity.ok(entryMediaService.updateEntryMedia(id, entryMedia));
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<?> deleteEntryMedia(@PathVariable Long id) {
        entryMediaService.deleteEntryMedia(id);
        return ResponseEntity.ok("Deleted entry media");
    }

    @GetMapping("/tables/{tableId}/entries")
    public ResponseEntity<?> getMediasByTable(@PathVariable Long tableId) {
        return ResponseEntity.ok(entryMediaService.getAllEntryMediaByTableId(tableId));
    }

    @GetMapping("/medias/{mediaId}/entries")
    public ResponseEntity<?> getMediasByMedia(@PathVariable Long mediaId) {
        return ResponseEntity.ok(entryMediaService.getAllEntryMediaByMediaId(mediaId));
    }

    @GetMapping("/tables/{tableId}/entries/full")
    public ResponseEntity<?> getFullMediasByTable(@PathVariable Long tableId) {
        return ResponseEntity.ok(entryMediaService.getFullEntryMediaByTable(tableId));
    }

}
