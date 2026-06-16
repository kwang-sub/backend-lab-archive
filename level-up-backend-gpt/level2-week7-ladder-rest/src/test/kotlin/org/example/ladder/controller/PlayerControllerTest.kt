package org.example.ladder.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.domain.Player
import org.example.ladder.dto.response.ErrorResponse
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.PlayerResponse
import org.example.ladder.repository.PlayerRepository
import org.example.ladder.support.RequestObjectFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val baseUri = "/api/v1/players"

    @Autowired
    private lateinit var playerRepository: PlayerRepository


    @Test
    @DisplayName("플레이어 생성 성공 - 유효한 이름을 입력했을 때")
    fun createBulk_shouldSuccess_whenValidName() {
        // given
        val playerBulkReqDto = RequestObjectFactory.createPlayerBulkReqDto(nameList = listOf("kwang", "james", "john"))
        val requestBody = objectMapper.writeValueAsString(playerBulkReqDto)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri + "/bulk")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val responsePage: PageResponse<PlayerResponse> = objectMapper.readValue(
            responseContent,
            object : TypeReference<PageResponse<PlayerResponse>>() {}
        )

        val pageContent = responsePage.content

        assertThat(responsePage.totalElements).isEqualTo(3)
        assertThat(pageContent.size).isEqualTo(3)
        assertThat(
            pageContent.containsAll(
                listOf(
                    PlayerResponse("kwang"),
                    PlayerResponse("james"),
                    PlayerResponse("john")
                )
            )
        ).isTrue
    }

    @Test
    @DisplayName("플레이어 생성 실패 - 빈 이름을 입력했을 때")
    fun createBulk_shouldFail_whenInvalidName() {
        val playerBulkReqDto = RequestObjectFactory.createPlayerBulkReqDto(nameList = listOf("", "james", "john"))
        val requestBody = objectMapper.writeValueAsString(playerBulkReqDto)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri + "/bulk")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andReturn()

        //then
        val responseContent = result.response.contentAsString
        val response: ErrorResponse = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.fieldErrors.size).isEqualTo(1)
        assertThat(response.fieldErrors[0].field).isEqualTo("players[0].name")
    }

    @Test
    @DisplayName("플레이어 생성 실패 - 중복된 이름을 입력했을 때")
    fun createBulk_shouldFail_whenEnablePlayers() {
        // given
        playerRepository.save(Player("kwang"))
        val playerBulkReqDto = RequestObjectFactory.createPlayerBulkReqDto(nameList = listOf("james", "john"))
        val requestBody = objectMapper.writeValueAsString(playerBulkReqDto)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri + "/bulk")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response: ErrorResponse = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(ServiceErrorCode.ALREADY_PLAYER.message)
    }

    @Test
    @DisplayName("플레이어 생성 실패 - 플레이어가 1명일 때")
    fun createPlayer_shouldFail_whenOneBulk() {
        // given
        val playerBulkReqDto = RequestObjectFactory.createPlayerBulkReqDto(nameList = listOf("john"))
        val requestBody = objectMapper.writeValueAsString(playerBulkReqDto)

        // when
        val result = mvc.perform(
            MockMvcRequestBuilders.post(baseUri + "/bulk")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andReturn()

        // then
        val responseContent = result.response.contentAsString
        val response: ErrorResponse = objectMapper.readValue(responseContent, ErrorResponse::class.java)
        assertThat(response.message).isEqualTo(ServiceErrorCode.INVALID_VALUE.message)
        assertThat(response.fieldErrors.size).isEqualTo(1)
        assertThat(response.fieldErrors[0].field).isEqualTo("players")
    }
}