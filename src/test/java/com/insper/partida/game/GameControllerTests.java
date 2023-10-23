package com.insper.partida.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import com.insper.partida.game.dto.GameReturnDTO;

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

    @Test
    void testGetAllGames() throws Exception {
        List<GameReturnDTO> gameReturnDTOList = new ArrayList<>();
        gameReturnDTOList.add(new GameReturnDTO());
        gameReturnDTOList.add(new GameReturnDTO());
        gameReturnDTOList.add(new GameReturnDTO());

        Mockito.when(gameService.listGames()).thenReturn(gameReturnDTOList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/games"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<GameReturnDTO> gameReturnDTOListResult = objectMapper.readValue(json, List.class);

        Assertions.assertEquals(gameReturnDTOList.size(), gameReturnDTOListResult.size());
    }

    @Test
    void testGetGameById() throws Exception {
        GameReturnDTO gameReturnDTO = new GameReturnDTO();
        gameReturnDTO.setIdentifier("123");
        gameReturnDTO.setStatus("SCHEDULED");

        Mockito.when(gameService.getGame("123")).thenReturn(gameReturnDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/games/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        GameReturnDTO gameReturnDTOResult = objectMapper.readValue(json, GameReturnDTO.class);

        Assertions.assertEquals(gameReturnDTO.getIdentifier(), gameReturnDTOResult.getIdentifier());
        Assertions.assertEquals(gameReturnDTO.getHome(), gameReturnDTOResult.getHome());
    }

    @Test
    void testGetGameByIdNotFound() throws Exception {
        Mockito.when(gameService.getGame("123")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/games/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    

}
