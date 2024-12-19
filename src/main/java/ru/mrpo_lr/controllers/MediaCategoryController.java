package ru.mrpo_lr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrpo_lr.models.MediaCategoryResponse;
import ru.mrpo_lr.services.mediaCategory.MediaCategoryService;

@RestController
@RequestMapping("/categories")
public class MediaCategoryController {
    private final MediaCategoryService mediaCategoryService;

    @Autowired
    public MediaCategoryController(MediaCategoryService mediaCategoryService) {
        this.mediaCategoryService = mediaCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMediaCategories() {
        return ResponseEntity.ok(this.mediaCategoryService.getMediaCategories());
    }

    @PostMapping
    public ResponseEntity<?> addMediaCategory(@RequestBody MediaCategoryResponse mediaCategoryResponse) {
        return ResponseEntity.ok(this.mediaCategoryService.addMediaCategory(mediaCategoryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMediaCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(this.mediaCategoryService.getMediaCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMediaCategory(@PathVariable Long id, @RequestBody MediaCategoryResponse mediaCategoryResponse) {
        return ResponseEntity.ok(this.mediaCategoryService.updateMediaCategory(id, mediaCategoryResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMediaCategory(@PathVariable Long id) {
        this.mediaCategoryService.deleteMediaCategory(id);
        return ResponseEntity.ok("Deleted");
    }
}
