package com.brainon.gestordeproyectos.repositories;

import com.brainon.gestordeproyectos.collections.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<Project, String> {

    Flux<Project> findByUserId(String userId);

}
