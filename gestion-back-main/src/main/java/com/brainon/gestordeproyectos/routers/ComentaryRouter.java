package com.brainon.gestordeproyectos.routers;

import com.brainon.gestordeproyectos.model.ComentaryDTO;
import com.brainon.gestordeproyectos.usecases.AddComentaryUseCase;
import com.brainon.gestordeproyectos.usecases.DeleteComentaryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ComentaryRouter {

    @Bean
    public RouterFunction<ServerResponse> addComentary(AddComentaryUseCase addComentaryUseCase) {
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ComentaryDTO.class)
                        .flatMap(addComentaryDTO -> addComentaryUseCase.saveComentary(addComentaryDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteComentary(DeleteComentaryUseCase deleteComentaryUseCase) {
        return route(
                DELETE("/comentary/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromPublisher(deleteComentaryUseCase.apply(
                                request.pathVariable("id")), String.class))
        );
    }

}
