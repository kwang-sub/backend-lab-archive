package org.example.ladder.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.domain.Player
import org.example.ladder.dto.response.ErrorResponse
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.support.RequestObjectFactory
import org.example.ladder.support.TestDomainFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestDomainFactory::class)
class RewardControllerTest {

    private val baseUri = "/api/v1/rewards"

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var testDomainFactory: TestDomainFactory

    @Test
    @DisplayName("보상 생성 성공 - 유효한 이름을 입력했을 때")
    fun create_shouldSuccess_whenValidName() {
        // given
        testDomainFactory.savePlayer(listOf("user1", "user2", "user3"))
        val rewardBulkReqDto = RequestObjectFactory.createRewardBulkReqDto(nameList = listOf("item1", "item2", "item3"))

        val requestBody = objectMapper.writeValueAsString(rewardBulkReqDto)

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
        val responsePage: PageResponse<RewardResponse> = objectMapper.readValue(
            responseContent,
            object : TypeReference<PageResponse<RewardResponse>>() {}
        )
        val actualRewardNameList = responsePage.content.map { it.name }

        assertThat(responsePage.content).hasSize(3)
        assertThat(actualRewardNameList).containsAll(rewardBulkReqDto.rewards.map { it.name })
    }

    @Test
    @DisplayName("보상 생성 실패 - 플레이어 수랑 맞지 않는 경우")
    fun create_shouldFail_whenNotMatchPlayerCount() {
        // given
        val rewardBulkReqDto = RequestObjectFactory.createRewardBulkReqDto(nameList = listOf("reward1", "reward2", "reward3"))

        val requestBody = objectMapper.writeValueAsString(rewardBulkReqDto)

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
        val response: ErrorResponse = objectMapper.readValue(
            responseContent,
            ErrorResponse::class.java
        )

        assertThat(response.message).isEqualTo(String.format(ServiceErrorCode.ENTITY_NOT_FOUND.message,  Player::class.java.simpleName))
    }

}