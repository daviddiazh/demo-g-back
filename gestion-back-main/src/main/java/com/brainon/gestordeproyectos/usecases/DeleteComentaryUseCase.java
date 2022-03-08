package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.repositories.ComentaryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteComentaryUseCase implements Function<String, Mono<String>> {

    private final ComentaryRepository comentaryRepository;

    public DeleteComentaryUseCase(ComentaryRepository comentaryRepository) {
        this.comentaryRepository = comentaryRepository;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El ID no puede estar vacio");

        return comentaryRepository.findById(id)
                .flatMap(project -> {
                    return comentaryRepository.deleteById(id).thenReturn(project.getProjectId());
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "id incorrecto")));

    }

}
