package com.brainon.gestordeproyectos.repositories;

import com.brainon.gestordeproyectos.collections.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUid(String uid);

}
