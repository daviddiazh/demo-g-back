package com.brainon.gestordeproyectos.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Document
public class Comentary {
    @Id
    private String id;
    private String userId;
    private String projectId;
    private String comentary;
    //private LocalDate fechaCreacio;
    private ArrayList<String> fechaCreacio;

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

    public ArrayList<String> getFechaCreacio() {
        return fechaCreacio;
    }

    public void setFechaCreacio(ArrayList<String> fechaCreacio) {
        this.fechaCreacio = fechaCreacio;
    }
}
