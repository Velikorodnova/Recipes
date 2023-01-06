package com.skypro.recipes.controller;

import com.skypro.recipes.service.impl.FileServiceRecipeImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FileControllerRecipe {

    private final FileServiceRecipeImpl fileServiceRecipe;

    public FileControllerRecipe(FileServiceRecipeImpl fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @GetMapping("/export/recipe")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileServiceRecipe.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileServiceRecipe.cleanDataFile();
        File dataFile = fileServiceRecipe.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
