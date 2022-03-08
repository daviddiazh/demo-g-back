package com.brainon.gestordeproyectos.repositories;

import com.brainon.gestordeproyectos.collections.Comentary;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ComentaryRepository extends ReactiveCrudRepository<Comentary, String> {

    Flux<Comentary> findAllByProjectId(String id);
    Mono<Void> deleteByProjectId(String projectId);
    Mono<Void> deleteAllByProjectId(String projectId);

}
