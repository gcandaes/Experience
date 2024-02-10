package com.example.retourexperience.service;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.UserMapper;
import com.example.retourexperience.repository.UserRepository;
import com.example.retourexperience.service.impl.UserServiceImpl;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;
    @Mock
    private Utils utils;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testCreateUser() {
        // Données de test
        UserDetailsRequestDtoModel userDetails = new UserDetailsRequestDtoModel();
        userDetails.setUserName("exampleUser");
        userDetails.setEmail("example@example.com");

        // Création d'une instance UserRest attendue
        UserRest expectedUser = new UserRest();
        expectedUser.setUserName("exampleUser");
        expectedUser.setEmail("example@example.com");

        // Simulation du comportement des dépendances (mocking)
        when(userMapper.mapToUserEntity(userDetails)).thenReturn(expectedUser);

        // Appel de la méthode à tester
        UserRest actualUser = userService.createUser(userDetails);

        // Vérification des interactions avec les dépendances
        verify(userMapper, times(1)).mapToUserEntity(userDetails);
        verify(userRepository, times(1)).save(expectedUser);

        // Vérification du résultat
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testUpdateUser() {
        // Données de test
        String userId = "123";
        UpdateUserDetailsRequestDtoModel updateUserDetails = new UpdateUserDetailsRequestDtoModel();
        updateUserDetails.setFirstName("jean");
        updateUserDetails.setLastName("dupond");

        // Création d'une instance UserRest attendue avant la mise à jour
        UserRest userBeforeUpdate = new UserRest();
        userBeforeUpdate.setUserId(userId);
        userBeforeUpdate.setUserName("originalUsername");
        userBeforeUpdate.setEmail("original@example.com");

        // Création d'une instance UserRest attendue après la mise à jour
        UserRest userUpdated = new UserRest();
        userUpdated.setUserId(userId);
        userUpdated.setUserName("updatedUsername");
        userUpdated.setEmail("updated@example.com");

        // Simulation du comportement des dépendances (mocking)
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userBeforeUpdate));
        when(userMapper.mapToUpdateUserEntity(updateUserDetails, userBeforeUpdate)).thenReturn(userUpdated);

        // Appel de la méthode à tester
        UserRest actualUser = userService.updateUser(userId, updateUserDetails);

        // Vérification des interactions avec les dépendances
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(userUpdated);
        verify(userMapper, times(1)).mapToUpdateUserEntity(updateUserDetails, userBeforeUpdate);

        // Vérification du résultat
        assertEquals(userUpdated, actualUser);
        assertEquals("updatedUsername", actualUser.getUsername());
        assertEquals("updated@example.com", actualUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        // Données de test
        String userId = "123";

        // Création d'une instance UserRest attendue avant la suppression
        UserRest userBeforeDelete = new UserRest();
        userBeforeDelete.setUserId(userId);
        userBeforeDelete.setUserName("usernameToDelete");
        userBeforeDelete.setEmail("delete@example.com");

        // Simulation du comportement de UserRepository.findById
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userBeforeDelete));

        // Appel de la méthode à tester
        userService.deleteUser(userId);

        // Vérification des interactions avec UserRepository
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(userBeforeDelete);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Données de test
        String userId = "123";

        // Simulation du comportement de UserRepository.findById pour un utilisateur non trouvé
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        // Appel de la méthode à tester
        assertThrows(UserServiceException.class, () -> userService.deleteUser(userId));

        // Vérification des interactions avec UserRepository
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).delete(any(UserRest.class));
    }
}
