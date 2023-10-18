package com.example.retourexperience.ui.model.responseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserRest {
    @Id
    private String userId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column
    private String password;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

}
