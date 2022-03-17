package com.brainon.gestordeproyectos.collections;

import com.brainon.gestordeproyectos.enums.Category;
import com.brainon.gestordeproyectos.enums.State;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Document
public class Project {
    @Id
    private String id;
    private String userId;
    private String project;
    private State state;
    private Category category;
    private String descripcion;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<String> getFechaCreacio() {
        return fechaCreacio;
    }

    public void setFechaCreacio(ArrayList<String> fechaCreacio) {
        this.fechaCreacio = fechaCreacio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
