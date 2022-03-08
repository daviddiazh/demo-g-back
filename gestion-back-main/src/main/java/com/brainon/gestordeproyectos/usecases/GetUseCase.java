package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.ComentaryDTO;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ComentaryRepository;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetUseCase implements Function<String, Mono<ProjectDTO>> {

    private final ProjectRepository projectRepository;
    private final ComentaryRepository comentaryRepository;
    private final MapperUtils mapperUtils;
    private final UserRepository userRepository;

    public GetUseCase(ProjectRepository projectRepository, ComentaryRepository comentaryRepository, MapperUtils mapperUtils, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.comentaryRepository = comentaryRepository;
        this.mapperUtils = mapperUtils;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<ProjectDTO> apply(String id) {
        Objects.requireNonNull(id, "El id es requerido");
        return projectRepository.findById(id)
                .map(mapperUtils.mapEntityToProject())
                .flatMap(mapProjectAggregate())
                .flatMap(mapUserProject());
    }

    private Function<ProjectDTO, Mono<ProjectDTO>> mapUserProject() {
        return projectDTO ->
                Mono.just(projectDTO).zipWith(
                        userRepository.findByUid(projectDTO.getUserId())
                                .map(mapperUtils.mapEntityToUser())
                        , (project, user) -> {
                            project.setUserDTO(user);
                            return project;
                        }
                );
    }

    private Function<ProjectDTO, Mono<ProjectDTO>> mapProjectAggregate() {
        return projectDTO ->
                Mono.just(projectDTO).zipWith(
                        comentaryRepository.findAllByProjectId(projectDTO.getId())
                                .map(mapperUtils.mapEntityToComentary())
                                .flatMap(mapUser())
                                .collectList(),
                        (project, comentary) -> {
                            project.setComentarys(comentary);
                            return project;
                        }
                );
    }

    private Function<ComentaryDTO, Mono<ComentaryDTO>> mapUser() {
        return comentaryDTO ->
                Mono.just(comentaryDTO).zipWith(
                        userRepository.findByUid(comentaryDTO.getUserId())
                                .map(mapperUtils.mapEntityToUser())
                        , (comentary, user) -> {
                            comentary.setUserDTO(user);
                            return comentary;
                        }
                );
    }

}

