package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.Technology;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;

public interface TechnologyService {
    Mono<Technology> saveTechnology(TechnologyDTO technologyDTO);
    void uploadTechnologyPhoto(String technologyName, FilePart photo);
    File downloadTechnologyPhoto(String technologyName);
}
