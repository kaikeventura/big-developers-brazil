package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.service.impl.TechnologyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyServiceImpl technologyService;

    @PostMapping("technologies")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<User>> createTechnology(@RequestBody @Valid final TechnologyDTO technologyDTO) {
        return Mono.just(new ResponseEntity(technologyService.saveTechnology(technologyDTO), HttpStatus.CREATED));
    }

    @PostMapping("technologies/photo/{technologyName}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<?>> uploadTechnologyPhoto(
            @PathVariable("technologyName") final String technologyName,
            @RequestPart(value = "photo") final FilePart photo
    ) {
        technologyService.uploadTechnologyPhoto(technologyName, photo);
        return Mono.just(new ResponseEntity(HttpStatus.NO_CONTENT));
    }

    @GetMapping("technologies/photo/download/{technologyName}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> downloadTechnologyPhoto(
            @PathVariable("technologyName") final String technologyName,
            ServerHttpResponse response
    ) {
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        File file = technologyService.downloadTechnologyPhoto(technologyName);

        return zeroCopyResponse.writeWith(file, 0, file.length());
    }

}
