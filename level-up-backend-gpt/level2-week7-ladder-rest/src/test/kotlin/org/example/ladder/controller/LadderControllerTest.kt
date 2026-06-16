package org.example.ladder.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.domain.Ladder
import org.example.ladder.domain.Player
import org.example.ladder.dto.request.LaderCommand
import org.example.ladder.dto.response.ErrorResponse
import org.example.ladder.dto.response.LadderResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.service.LadderGameService
import org.example.ladder.support.TestDomainFactory
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDomainFactory::class)
class LadderControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper
    private val baseUri = "/api/v1/ladders"

    @Autowired
    private lateinit var testDomainFactory: TestDomainFactory

    @MockK
    private lateinit var ladderGameService: LadderGameService

    @Test
    @DisplayName("사다리 생성 성공 - 유효한 요청을 보냈을 때")
    fun createLadder_shouldSuccess_whenValidRequest() {
        // given
        testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))
        val command = LaderCommand(5)
        val requestBody = objectMapper.writeValueAsString(command)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, LadderResponse::class.java)

        assertThat(response.lines.size).isEqualTo(5)
    }

    @Test
    @DisplayName("사다리 생성 실패 - 유효하지 않은 요청을 보냈을 때")
    fun createLadder_shouldFail_whenInvalidRequest() {
        // given
        val command = LaderCommand(0) // 유효하지 않은 라인 수
        val requestBody = objectMapper.writeValueAsString(command)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.fieldErrors).isNotEmpty
        assertThat(response.fieldErrors[0].field).isEqualTo("lineCount")
    }

    @Test
    @DisplayName("사다리 생성 실패 - 플레이어 수와 보상 수가 일치하지 않을 때")
    fun createLadder_shouldFail_whenPlayerCountNotMatchRewardCount() {
        // given
        testDomainFactory.savePlayer(listOf("user1", "user2"))
        testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3")) // 보상 수가 플레이어 수와 다름
        val command = LaderCommand(5)
        val requestBody = objectMapper.writeValueAsString(command)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(ServiceErrorCode.MISMATCHED_PLAYER_AND_REWARD_COUNT.message)
    }

    @Test
    @DisplayName("사다리 생성 실패 - 이미 사다리가 존재할 때")
    fun createLadder_shouldFail_whenLadderAlreadyExists() {
        // given
        val savePlayer = testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))
        testDomainFactory.saveLadder(5, savePlayer.size - 1)

        val command = LaderCommand(5)
        val requestBody = objectMapper.writeValueAsString(command)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(ServiceErrorCode.ALREADY_EXIST_LADDER.message)
    }


    // TODO 로직 작성 필요
    @Test
    @DisplayName("사다리 조회 성공 - 사다리가 존재할 때")
    fun getLadder_shouldSuccess_whenLadderExists() {
        // given
        val savePlayer = testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))
        testDomainFactory.saveLadder(5, savePlayer.size - 1)

        val command = LaderCommand(5)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, LadderResponse::class.java)
        assertThat(response.lines.size).isEqualTo(5)
    }

    @Test
    @DisplayName("사다리 조회 실패 - 사다리가 존재하지 않을 때")
    fun getLadder_shouldFail_whenLadderNotExists() {
        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(ServiceErrorCode.ENTITY_NOT_FOUND.message + Ladder::class.java.simpleName)
    }

    
    // TODO 코틀린 mockk 사용법 익히기 필요(Mockito 동작 안함)
//    @Test
//    @DisplayName("사다리 결과 조회 성공 - 사다리가 존재하고 플레이어 이름이 존재할 때")
    fun getLadderResult_shouldSuccess_whenLadderExistsAndPlayerNameExists() {
        // given
        val savePlayer = testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        val saveReward = testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))
        val saveLadder = testDomainFactory.saveLadder(5, savePlayer.size - 1)


        every {
            ladderGameService.playGame(any(), any(), any(), savePlayer[0].name)
        } returns saveReward[0]

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri + "/play")
                .queryParam("name", savePlayer[0].name)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, RewardResponse::class.java)
        assertThat(response.name).isEqualTo(saveReward[0].name)

        verify(exactly = 1) {
            ladderGameService.playGame(any(), any(), any(), savePlayer[0].name)
        }
    }


    @Test
    @DisplayName("사다리 결과 조회 실패 - 사다리가 존재하고 플레이어 이름이 존재하지 않을 때")
    fun getLadderResult_shouldFail_whenLadderExistsAndPlayerNameNotExists() {
        // given
        val savePlayer = testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        val saveReward = testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))
        val saveLadder = testDomainFactory.saveLadder(5, savePlayer.size - 1)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri + "/play")
                .queryParam("name", "unknownPlayer")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(String.format(ServiceErrorCode.ENTITY_NOT_FOUND.message, Player::class.simpleName) )
    }

    @Test
    @DisplayName("사다리 결과 조회 실패 - 사다리가 존재하지 않을 때")
    fun getLadderResult_shouldFail_whenLadderNotExists() {
        // given
        val savePlayer = testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        val saveReward = testDomainFactory.saveReward(listOf("reward1", "reward2", "reward3"))

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.get(baseUri + "/play")
                .queryParam("name", "user1")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(String.format(ServiceErrorCode.ENTITY_NOT_FOUND.message, Ladder::class.simpleName) )
    }
}