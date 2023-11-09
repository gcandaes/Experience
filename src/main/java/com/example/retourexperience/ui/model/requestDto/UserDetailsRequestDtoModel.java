package com.example.retourexperience.ui.model.requestDto;

import com.example.retourexperience.shared.UniqueEmail;
import com.example.retourexperience.shared.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDtoModel {

    @NotNull(message = "l'adresse email doit être renseignée")
    @Email
    @UniqueEmail
    private String email;
    @NotNull(message = "le mot de passe doit être renseigné")
    @Size(min = 8, message = "le mot de passe doit être au moins de 8 caractères")
    private String password;
    @Size(min = 2, message = "votre nom d'uilisateur doit être de 2 caractères minimum")
    @UniqueUsername
    private String userName;

}
