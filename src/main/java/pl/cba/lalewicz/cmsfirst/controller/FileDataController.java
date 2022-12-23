package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.cba.lalewicz.cmsfirst.entity.FileData;
import pl.cba.lalewicz.cmsfirst.service.FileDataService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class FileDataController {
    @Autowired
    private FileDataService fileDataService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws IOException {
        return fileDataService.uploadFile(file,description);
    }
    @GetMapping("/allfiles")
    public List<FileData> getListOfFile() {
        return fileDataService.getListOfFile();
    }
}
