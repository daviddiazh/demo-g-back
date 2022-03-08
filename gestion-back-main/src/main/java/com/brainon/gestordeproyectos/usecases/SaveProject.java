package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.ProjectDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveProject {

    Mono<String> apply(@Valid ProjectDTO projectDTO);

}
