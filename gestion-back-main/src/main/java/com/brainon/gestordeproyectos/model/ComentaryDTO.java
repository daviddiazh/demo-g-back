package com.brainon.gestordeproyectos.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class ComentaryDTO {
    private String id;
    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String projectId;
    @NotBlank
    private String comentary;
    private UserDTO userDTO;
    private LocalDate fechaCreacio = LocalDate.now();

    public ComentaryDTO() {
    }

    public ComentaryDTO(String id, String projectId, @NotBlank String userId, @NotBlank String comentary ) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.comentary = comentary;
        this.fechaCreacio = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public LocalDate getFechaCreacio() {
        return fechaCreacio;
    }

    public void setFechaCreacio(LocalDate fechaCreacio) {
        this.fechaCreacio = fechaCreacio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentaryDTO that = (ComentaryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(projectId, that.projectId) && Objects.equals(comentary, that.comentary) && Objects.equals(userDTO, that.userDTO) && Objects.equals(fechaCreacio, that.fechaCreacio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, projectId, comentary, userDTO, fechaCreacio);
    }
}
