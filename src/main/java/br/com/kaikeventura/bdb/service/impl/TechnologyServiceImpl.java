package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.error.exception.BigDebugNotAvailableException;
import br.com.kaikeventura.bdb.error.exception.BigDebugNotFoundException;
import br.com.kaikeventura.bdb.error.exception.RetrievingTechnologyPhotoException;
import br.com.kaikeventura.bdb.error.exception.TechnologyAlreadyRegisteredException;
import br.com.kaikeventura.bdb.model.Technology;
import br.com.kaikeventura.bdb.repository.TechnologyRepository;
import br.com.kaikeventura.bdb.repository.TechnologyRepositoryReactive;
import br.com.kaikeventura.bdb.service.TechnologyService;
import br.com.kaikeventura.bdb.util.TechnologyUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final TechnologyRepositoryReactive technologyRepositoryReactive;
    private final TechnologyUtil technologyUtil;
    private final AmazonS3 amazonS3;

    private static final String bucketName = "bdb-bucket";

    @Override
    public Mono<Technology> saveTechnology(TechnologyDTO technologyDTO) {
        verifyIfTechnologyIsExists(technologyDTO.getName());

        return technologyRepositoryReactive.save(technologyUtil.toNewTechnology(technologyDTO));
    }

    @Override
    public void uploadTechnologyPhoto(String technologyName, FilePart photo) {
        Technology technology = getTechnology(technologyName);
        final String photoId = generateFileName(technologyName);

        File readyPhoto = buildPhoto(photo);
        uploadPhotoS3Bucket(photoId, readyPhoto);
        technology.setPhotoId(photoId);

        technologyRepository.save(technology);
    }

    @Override
    public File downloadTechnologyPhoto(String technologyName) {
        Technology technology = getTechnology(technologyName);
        File file = new File(technologyName);
        try {
            IOUtils.copy(
                    amazonS3.getObject(bucketName, technology.getPhotoId()).getObjectContent(), new FileOutputStream(file)
            );
        } catch (IOException e) {
            throw new RetrievingTechnologyPhotoException();
        }

        return file;
    }

    private void verifyIfTechnologyIsExists(String name) {
        technologyRepository.findByName(name).ifPresent(ex -> {
            throw new TechnologyAlreadyRegisteredException();
        });
    }

    private File buildPhoto(FilePart photo) {
        File convFile = new File(Objects.requireNonNull(photo.filename()));
        photo.transferTo(convFile);

        return convFile;
    }

    private String generateFileName(String technologyName) {
        return UUID.randomUUID().toString()
                .concat("&&")
                .concat(String.valueOf(new Date().getTime()))
                .concat("-")
                .concat(technologyName.replace(" ", "_"));
    }

    private void uploadPhotoS3Bucket(String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

    private Technology getTechnology(String technologyName) {
        Optional<Technology> technology = technologyRepository.findByName(technologyName);
        if (technology.isEmpty()) {
            throw new BigDebugNotFoundException();
        }
        if (!technology.get().getActive()) {
            throw new BigDebugNotAvailableException();
        }

        return technology.get();
    }

}
