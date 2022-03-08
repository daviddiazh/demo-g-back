package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.collections.Project;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUseCase implements SaveProject{

    private final ProjectRepository projectRepository;
    private final MapperUtils mapperUtils;

    public CreateUseCase(ProjectRepository projectRepository, MapperUtils mapperUtils) {
        this.projectRepository = projectRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProjectDTO newProject){
        return projectRepository
                .save(mapperUtils.mapperToProject(null).apply(newProject))
                .map(Project::getId);

    }

}
