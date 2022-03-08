package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.collections.Project;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateUseCase implements SaveProject {

    private final ProjectRepository projectRepository;
    private final MapperUtils mapperUtils;

    public UpdateUseCase(ProjectRepository projectRepository, MapperUtils mapperUtils) {
        this.projectRepository = projectRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProjectDTO projectDTO) {
        Objects.requireNonNull(projectDTO.getId(), "El Id del proyecto es requerido");
        return projectRepository
                .save(mapperUtils.mapperToProject(projectDTO.getId()).apply(projectDTO))
                .map(Project::getId);
    }

}
