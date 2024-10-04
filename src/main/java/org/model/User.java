package org.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class User extends PanacheEntity{
    String username;
    String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User() {
    }

    public static User findByUsernameAndPassword(String username,String password){
        return find("username = ?1 and password = ?2",username,password).firstResult();
    }
    public static User findByUsername(String username){
        return find("username = ?1",username).firstResult();
    }
}
