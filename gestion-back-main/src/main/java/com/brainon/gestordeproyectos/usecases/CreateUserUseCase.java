package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.UserDTO;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUserUseCase implements SaveUser{

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;

    public CreateUserUseCase(UserRepository userRepository, MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO){
        return userRepository.findByUid(userDTO.getUid())
                .flatMap(user -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT));
                }).switchIfEmpty(userRepository.save(mapperUtils.mapperToUser(null).apply(userDTO))).thenReturn("usuario guardado");
    }

}
