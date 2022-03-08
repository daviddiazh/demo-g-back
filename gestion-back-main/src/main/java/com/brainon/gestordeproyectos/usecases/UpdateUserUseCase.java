package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.model.UserDTO;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UpdateUserUseCase implements Function<UserDTO, Mono<UserDTO>> {

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;


    public UpdateUserUseCase(UserRepository userRepository, MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<UserDTO> apply(UserDTO userDTO) {
        Objects.requireNonNull(userDTO.getUid(), "El Id del proyecto es requerido");
        return userRepository.findByUid(userDTO.getUid())
                .flatMap(user -> {
                    user.setName(userDTO.getName());
                    return userRepository.save(user);
                }).map(mapperUtils.mapEntityToUser());
    }

}
