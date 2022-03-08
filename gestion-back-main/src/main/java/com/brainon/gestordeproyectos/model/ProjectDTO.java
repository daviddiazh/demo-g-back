package com.brainon.gestordeproyectos.model;


import com.brainon.gestordeproyectos.enums.Category;
import com.brainon.gestordeproyectos.enums.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProjectDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String project;
    @NotNull
    private State state;
    @NotNull
    private Category category;
    private List<ComentaryDTO> comentarys;
    private UserDTO userDTO;
    private String email;
    private LocalDate fechaCreacion = LocalDate.now();
    @NotBlank
    private String descripcion;

    public ProjectDTO(){

    }

    public ProjectDTO(String userId, String project, State state, Category category) {
        this.userId = userId;
        this.project = project;
        this.state=state;
        this.category=category;

    }

    public ProjectDTO(String id, String userId, String project, State state, Category category ,String descripcion) {
        this.id = id;
        this.userId = userId;
        this.project = project;
        this.state = state;
        this.category = category;
        this.descripcion = descripcion;
        this.fechaCreacion=LocalDate.now();
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public List<ComentaryDTO> getComentarys() {
        this.comentarys = Optional.ofNullable(comentarys).orElse(new ArrayList<>());
        return comentarys;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setComentarys(List<ComentaryDTO> comentarys) {
        this.comentarys = comentarys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public State getState() {
        return state;
    }

    public void setType(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(project, that.project) && state == that.state && category == that.category && Objects.equals(comentarys, that.comentarys) && Objects.equals(userDTO, that.userDTO) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, project, state, category, comentarys, userDTO, fechaCreacion, descripcion);
    }
}
