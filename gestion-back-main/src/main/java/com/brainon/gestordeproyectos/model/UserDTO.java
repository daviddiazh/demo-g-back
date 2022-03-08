package com.brainon.gestordeproyectos.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDTO {

    private String id;
    @NotBlank
    private String uid;
    private String name;
    @NotBlank
    private String email;
    private String pictureUrl;

    public UserDTO(String id, String uid, String name, String email, String pictureUrl) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(uid, userDTO.uid) && Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email) && Objects.equals(pictureUrl, userDTO.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, name, email, pictureUrl);
    }

}
