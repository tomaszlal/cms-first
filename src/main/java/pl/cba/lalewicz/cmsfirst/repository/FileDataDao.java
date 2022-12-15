package pl.cba.lalewicz.cmsfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cba.lalewicz.cmsfirst.entity.FileData;

import java.util.ArrayList;
import java.util.Optional;
@Repository
public interface FileDataDao extends JpaRepository<FileData,Long> {

    Optional<ArrayList<FileData>> findAllByName(String name);
    Optional<FileData> findByName(String name);
}
