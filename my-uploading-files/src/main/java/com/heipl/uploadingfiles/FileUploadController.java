package com.heipl.uploadingfiles;

import com.heipl.uploadingfiles.storage.exception.StorageFileNotFoundException;
import com.heipl.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    //@formatter:off
    private final Function<Path, String> pathMappingFunction = path ->
        MvcUriComponentsBuilder.fromMethodName(
                FileUploadController.class,
                "serveFile",
                path.getFileName().toString()
        )
        .build()
        .toUri()
        .toString(); //@formatter:on

    @Autowired
    public FileUploadController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(final Model model) {

        final List<String> filesList = storageService.loadAll()
                                                     .map(pathMappingFunction)
                                                     .collect(Collectors.toList());
        model.addAttribute("files", filesList);
        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable final String filename) {

        final Resource file = storageService.loadAsResource(filename);
        final String headerValue = "attachment; filename=\"" + file.getFilename() + "\"";
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                             .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}