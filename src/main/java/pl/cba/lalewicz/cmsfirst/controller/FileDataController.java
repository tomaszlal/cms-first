package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.cba.lalewicz.cmsfirst.service.FileDataService;

import java.io.IOException;

@CrossOrigin
@RestController
public class FileDataController {
    @Autowired
    private FileDataService fileDataService;

    @PostMapping("/upload")
    private String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileDataService.uploadFile(file);
    }
}
