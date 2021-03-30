package com.heipl.uploadingfiles;

import com.heipl.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(final Model model) throws IOException {
        final Function<Path, UriComponentsBuilder> mappingFunction = path -> {
            return MvcUriComponentsBuilder.fromMethodName(
                    FileUploadController.class,
                    "serveFile",
                    path.getFileName().toString()
            );
        };

        final Stream<UriComponentsBuilder> uriComponentsBuilderStream = storageService.loadAll()
                                                                                      .map(mappingFunction::apply);
        final List<String> filesList = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(
                            FileUploadController.class,
                            "serveFile",
                            path.getFileName().toString()
            ).build()
            .toUri()
            .toString())
            .collect(Collectors.toList());

        model.addAttribute("files", filesList);

        return "uploadForm";
    }

}