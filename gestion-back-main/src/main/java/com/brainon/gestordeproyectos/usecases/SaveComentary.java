package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.ComentaryDTO;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveComentary {

    //Mono<Void> saveComentary(@Valid ComentaryDTO comentaryDTO);

    //Mono<ProjectDTO> saveComentary(@Valid ComentaryDTO comentaryDTO);

    Mono<ComentaryDTO> saveComentary(@Valid ComentaryDTO comentaryDTO);

}
