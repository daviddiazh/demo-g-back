package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class OwnerListUseCase implements Function<String, Flux<ProjectDTO>> {

    private final ProjectRepository projectRepository;
    private final MapperUtils mapperUtils;
    private final UserRepository userRepository;

    public OwnerListUseCase(ProjectRepository projectRepository, MapperUtils mapperUtils, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.mapperUtils = mapperUtils;
        this.userRepository = userRepository;
    }

    @Override
    public Flux<ProjectDTO> apply(String userId) {
        return projectRepository.findByUserId(userId)
                .map(mapperUtils.mapEntityToProject())
                .flatMap(projectDTO -> {
                    return userRepository.findByUid(projectDTO.getUserId())
                            .map(user -> {
                                projectDTO.setUserDTO(mapperUtils.mapEntityToUser().apply(user));
                                return projectDTO;
                            });
                });
    }

}
