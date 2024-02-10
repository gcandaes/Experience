package com.example.retourexperience.service;

import com.example.retourexperience.mapper.ExperienceMapper;
import com.example.retourexperience.repository.*;
import com.example.retourexperience.service.impl.ExperienceServiceImpl;
import com.example.retourexperience.service.impl.UserServiceImpl;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.entity.UserRest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExperienceServiceTest {

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private ExperienceMapper experienceMapper;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private WorkRepository workRepository;

    @Test
    @WithMockUser(username = "testuser")
    public void testUpdateExperience() {
        // Données de test
        String experienceId = "123";
        Place place = new Place("01", "auberge", "59000", "Lille" );
        Place placeUpdated = new Place("02", "auberge", "62575", "Blendecques" );

        Experience experienceToUpdate = new Experience();
        UserRest currentUser = new UserRest();
        currentUser.setUserName("utilisateur1");
        currentUser.setUserId("01");

        // ... Initialisez vos données d'expérience ...

        // Création d'une instance Experience attendue avant la mise à jour
        Experience experienceBeforeUpdate = new Experience();
        experienceBeforeUpdate.setPlace(place);
        // ... Initialisez vos données d'expérience avant mise à jour ...

        // Création d'une instance Experience attendue après la mise à jour
        Experience experienceUpdated = new Experience();
        experienceUpdated.setPlace(placeUpdated);
        // ... Initialisez vos données d'expérience après mise à jour ...

        // Simulation du comportement de ExperienceRepository.findById
        when(experienceRepository.findById(experienceId)).thenReturn(java.util.Optional.of(experienceBeforeUpdate));

        // Simulation du comportement de ExperienceMapper.mapToUpdateExperienceEntity
        when(experienceMapper.mapToUpdateExperienceEntity(experienceToUpdate, experienceBeforeUpdate))
                .thenReturn(experienceUpdated);

        // Simulation du comportement des repositories pour les entités associées
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(new UserRest()));
        //when( userService.getUserByUsername(any())).thenReturn(currentUser);


        // Appel de la méthode à tester
        Experience actualExperience = experienceService.updateExperience(experienceId, experienceToUpdate);

        // Vérification des interactions avec ExperienceRepository et ExperienceMapper
        verify(experienceRepository, times(1)).findById(experienceId);
        verify(experienceMapper, times(1)).mapToUpdateExperienceEntity(experienceToUpdate, experienceBeforeUpdate);

        // Vérification du résultat
        assertEquals(experienceUpdated, actualExperience);
        assertEquals(experienceUpdated.getPlace(), actualExperience.getPlace());
        assertNotEquals(actualExperience.getPlace(), experienceToUpdate.getPlace());
        // ... Ajoutez d'autres vérifications selon vos besoins ...
    }

    @Test
    @WithMockUser(username = "utilisateur1")
    public void getExperiencesTest(){
        Experience experience1 = new Experience();
        Experience experience2 = new Experience();
        Experience experience3 = new Experience();

        Set<Experience> experiencesCurrentUser = new HashSet<>(Arrays.asList(experience1, experience2));
        Set<Experience> experiencesOtherUser = new HashSet<>(Arrays.asList(experience3));
        UserRest currentUser = new UserRest();
        currentUser.setUserName("utilisateur1");
        currentUser.setUserId("01");
        currentUser.setExperiences(experiencesCurrentUser);

        UserRest otherUser = new UserRest();
        otherUser.setUserName("utilisateur2");
        otherUser.setUserId("02");
        otherUser.setExperiences(experiencesOtherUser);
        when(userRepository.findByUserName(any())).thenReturn(currentUser);
        when(experienceRepository.findByUserIdentification(any())).thenReturn(experiencesCurrentUser.stream().toList());
        List<Experience> experiences = experienceService.getExperiences();

        assertEquals(experiences, currentUser.getExperiences().stream().toList());
    }

}



