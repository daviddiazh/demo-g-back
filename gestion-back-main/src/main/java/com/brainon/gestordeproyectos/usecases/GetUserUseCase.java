package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.UserDTO;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetUserUseCase implements Function<String, Mono<UserDTO>> {

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;

    public GetUserUseCase(UserRepository userRepository, MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<UserDTO> apply(String s) {
        return userRepository.findByUid(s)
                .map(mapperUtils.mapEntityToUser())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
