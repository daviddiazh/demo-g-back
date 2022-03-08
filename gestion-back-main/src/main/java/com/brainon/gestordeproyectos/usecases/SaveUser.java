package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.UserDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveUser {

    Mono<String> apply(@Valid UserDTO userDTO);

}
