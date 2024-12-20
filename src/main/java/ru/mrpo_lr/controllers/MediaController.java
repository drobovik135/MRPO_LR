package ru.mrpo_lr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrpo_lr.entity.MediaCategory;
import ru.mrpo_lr.models.MediaResponse;
import ru.mrpo_lr.services.media.MediaService;

import java.util.List;

@RestController
public class MediaController {
    private final MediaService mediaService;


    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/medias")
    public ResponseEntity<?> getMedia() {
        return ResponseEntity.ok(mediaService.getMedias());
    }

    @GetMapping("/medias/{id}")
    public ResponseEntity<?> getMediaById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @PutMapping("/medias/{id}")
    public ResponseEntity<?> updateMedia(@PathVariable Long id, @RequestBody MediaResponse mediaResponse) {
        return ResponseEntity.ok(mediaService.updateMedia(id, mediaResponse));
    }

    @DeleteMapping("/medias/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/categories/{id}/media")
    public ResponseEntity<?> addCategory(@PathVariable Long id, @RequestBody MediaResponse mediaResponse) {
        return ResponseEntity.ok(mediaService.addMedia(id, mediaResponse));
    }

    @GetMapping("/categories/{id}/media")
    public ResponseEntity<?> getMediaByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.mediasByCategory(id));
    }
}