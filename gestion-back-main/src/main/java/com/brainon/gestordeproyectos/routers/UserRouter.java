package com.brainon.gestordeproyectos.routers;

import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.model.UserDTO;
import com.brainon.gestordeproyectos.usecases.CreateUserUseCase;
import com.brainon.gestordeproyectos.usecases.GetUserUseCase;
import com.brainon.gestordeproyectos.usecases.OwnerListUseCase;
import com.brainon.gestordeproyectos.usecases.UpdateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> createUser(CreateUserUseCase createUseCase) {
        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> createUseCase.apply(userDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateUser(UpdateUserUseCase updateUseCase) {
        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> updateUseCase.apply(userDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updateUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getUser(GetUserUseCase getUseCase) {
        return route(
                GET("/getUser/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                request.pathVariable("id")), UserDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getOwnerAll(OwnerListUseCase ownerListUseCase) {
        return route(
                GET("/getOwnerAll/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                ownerListUseCase.apply(request.pathVariable("userId")),
                                ProjectDTO.class
                        ))
        );
    }

}
