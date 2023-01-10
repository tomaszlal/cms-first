package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cba.lalewicz.cmsfirst.entity.FileData;
import pl.cba.lalewicz.cmsfirst.repository.FileDataDao;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FileDataService {

    @Value("${myConfig.folderPath}")
    private String folderPath;
    @Value("${myConfig.folder}")
    private String folder;

    @Autowired
    private FileDataDao fileDataDao;

    public ResponseEntity uploadFile(MultipartFile file, String description) throws IOException {
        String filePath = folderPath + file.getOriginalFilename();
        try {
            fileDataDao.findByName(file.getOriginalFilename()).get(); //sprawdzenie unikalnej nazwy pliku w storage
        } catch (NoSuchElementException exception) {
            final DatagramSocket datagramSocket = new DatagramSocket();//pobranie adresu ip
            try {
                datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            } catch (Exception e) {
            }
            String ip = datagramSocket.getLocalAddress().getHostAddress();
            String fileAdress = "http://" + ip + folder + file.getOriginalFilename();
//            System.out.println(fileAdress);

            FileData fileData = fileDataDao.save(new FileData(file.getOriginalFilename(), fileAdress, file.getContentType(), description));

            file.transferTo(new File(filePath));
            if (fileData != null) return new ResponseEntity<>(HttpStatus.OK);
            else return null;
        }

        //plik istnieje
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public List<FileData> getListOfFile() {


        return fileDataDao.findAll(Sort.by("id").descending());
    }

    public void changeIp(String oldIp, String newIp) {
//        String oldIp, String newIp

//        final String oldIp = "192.168.0.58";
//        final String newIp = "192.168.10.250";
//        final String newIp = "192.168.0.58";
//        final String oldIp = "192.168.10.250";
        List<FileData> fileDataList = fileDataDao.findAll();

        fileDataList.forEach(file -> {
            file.setFilePath(file.getFilePath().replaceAll(oldIp, newIp));
        });
        fileDataList.stream().forEach(fileData -> {
            System.out.println(fileData.getFilePath().toString());
        });
        fileDataDao.saveAll(fileDataList);
    }

}
