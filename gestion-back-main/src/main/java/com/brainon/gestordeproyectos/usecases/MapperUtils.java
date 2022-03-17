package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.collections.Comentary;
import com.brainon.gestordeproyectos.collections.Project;
import com.brainon.gestordeproyectos.collections.User;
import com.brainon.gestordeproyectos.model.ComentaryDTO;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<ComentaryDTO, Comentary> mapperToComentary() {
        return updateComentary -> {
            var comentary = new Comentary();
            comentary.setId(updateComentary.getId());
            comentary.setProjectId(updateComentary.getProjectId());
            comentary.setUserId(updateComentary.getUserId());
            comentary.setComentary(updateComentary.getComentary());
            comentary.setFechaCreacio(updateComentary.getFechaCreacio());
            return comentary;
        };
    }

    public Function<ProjectDTO, Project> mapperToProject(String id) {
        return updateProject -> {
            var project = new Project();
            project.setId(id);
            project.setUserId(updateProject.getUserId());
            project.setCategory(updateProject.getCategory());
            project.setProject(updateProject.getProject());
            project.setUserId(updateProject.getUserId());
            project.setState(updateProject.getState());
            project.setFechaCreacio(updateProject.getFechaCreacion());
            project.setDescripcion(updateProject.getDescripcion());
            return project;
        };
    }

    public Function<Project, ProjectDTO> mapEntityToProject(){
        return entity -> new ProjectDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getProject(),
                entity.getState(),
                entity.getCategory() ,
                entity.getDescripcion(),
                entity.getFechaCreacio());
    }

    public Function<Comentary, ComentaryDTO> mapEntityToComentary() {
        return entity -> new ComentaryDTO(
                entity.getId(),
                entity.getProjectId(),
                entity.getUserId(),
                entity.getComentary(),
                entity.getFechaCreacio()
        );
    }

    public Function<User, UserDTO> mapEntityToUser() {
        return entity -> new UserDTO(
                entity.getId(),
                entity.getUid(),
                entity.getName(),
                entity.getEmail(),
                entity.getPictureUrl(),
                entity.getAdmin());
    }

    public Function<UserDTO, User> mapperToUser(String id) {
        return updateUser -> {
            var user = new User();
            user.setId(id);
            user.setUid(updateUser.getUid());
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPictureUrl(updateUser.getPictureUrl());
            user.setAdmin(updateUser.getAdmin());
            return user;
        };
    }

}
