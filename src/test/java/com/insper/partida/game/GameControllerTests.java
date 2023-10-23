package com.insper.partida.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GameControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    GameController gameController;

    @Mock
    GameService gameService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(gameController)
                .build();
    }

    

}
