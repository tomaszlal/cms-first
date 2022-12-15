package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cba.lalewicz.cmsfirst.entity.FileData;
import pl.cba.lalewicz.cmsfirst.repository.FileDataDao;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class FileDataService {

    @Value("${myConfig.folderPath}")
    private String folderPath;

    @Autowired
    private FileDataDao fileDataDao;

    public String uploadFile(MultipartFile file) throws IOException {
        String filePath = folderPath + file.getOriginalFilename();

        try {
            fileDataDao.findByName(file.getOriginalFilename()).get();
        } catch (NoSuchElementException exception){
            FileData fileData = fileDataDao.save(new FileData(file.getOriginalFilename(), filePath, file.getContentType()));

            file.transferTo(new File(filePath));
            if (fileData != null) return filePath;
            else return null;

        }

        return "plik istnieje";
    }


}
