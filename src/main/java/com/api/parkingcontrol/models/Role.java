package com.api.parkingcontrol.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy="roles")
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Role(String name) {
        this.name=name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
