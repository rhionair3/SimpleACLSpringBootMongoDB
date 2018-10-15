package com.rwds.login.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")

public class User {
    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String surel;
    private String katasandi;
    private String namalengkap;
    private boolean statusaktivasi;

    @DBRef
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurel() {
        return surel;
    }

    public void setSurel(String surel) {
        this.surel = surel;
    }

    public String getKatasandi() {
        return katasandi;
    }

    public void setKatasandi(String katasandi) {
        this.surel = katasandi;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public boolean isStatusaktivasi() {
        return statusaktivasi;
    }

    public void setStatusaktivasi(boolean statusaktivasi) {
        this.statusaktivasi = statusaktivasi;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
