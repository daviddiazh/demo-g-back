package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ComentaryRepository;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteUseCase implements Function<String, Mono<ProjectDTO>> {

    private final ProjectRepository projectRepository;
    private final ComentaryRepository comentaryRepository;
    private final MapperUtils mapperUtils;

    public DeleteUseCase(ProjectRepository projectRepository, ComentaryRepository comentaryRepository, MapperUtils mapperUtils) {
        this.projectRepository = projectRepository;
        this.comentaryRepository = comentaryRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<ProjectDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");

        return projectRepository.findById(id)
                .flatMap(question -> {
                    return projectRepository.deleteById(id)
                            .and(comentaryRepository.deleteAllByProjectId(id)).thenReturn(question).map(mapperUtils.mapEntityToProject());
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "id erroneo")));
    }

}
