package org.example.ladder.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.example.ladder.dto.response.RewardResponse;
import org.example.ladder.service.LadderGameService;
import org.example.ladder.support.TestDomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDomainFactory.class)
public class LadderControllerMockTest {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;
  private final String baseUri = "/api/v1/ladders";

  @Autowired
  private TestDomainFactory testDomainFactory;

  @MockitoBean
  private LadderGameService ladderGameService;


  @Test
  @DisplayName("사다리 결과 조회 성공 - 사다리가 존재하고 플레이어 이름이 존재할 때")
  public void getLadderResult_shouldSuccess_whenLadderExistsAndPlayerNameExistsV2() throws Exception {
    // given
    var savePlayer = testDomainFactory.savePlayer(List.of("user1", "user2", "user3"));
    var saveReward = testDomainFactory.saveReward(List.of("reward1", "reward2", "reward3"));
    var saveLadder = testDomainFactory.saveLadder(5, savePlayer.size() - 1);

    Mockito.when(ladderGameService.playGame(any(), any(), any(), eq(savePlayer.get(0).getName())))
        .thenReturn(saveReward.get(0));

    // when
    MvcResult result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri + "/play")
                .queryParam("name", savePlayer.get(0).getName())
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andReturn();

    // then
    String responseContent = result.getResponse().getContentAsString();
    RewardResponse response = objectMapper.readValue(responseContent, RewardResponse.class);

    assertThat(response.getName()).isEqualTo(saveReward.get(0).getName());

    verify(ladderGameService, times(1))
        .playGame(any(), any(), any(), eq(savePlayer.get(0).getName()));
  }

}
