package com.example.retourexperience;

import com.example.retourexperience.ui.controller.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RetourExperienceApplicationTests {

    @Autowired
    private LoginController controller;

    @Test
    void contextLoads() {
    assertThat(controller).isNotNull();
    }


}
