package org.example.ladder.controller

import jakarta.validation.Valid
import org.example.ladder.dto.request.RewardBulkCommand
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.service.RewardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/rewards")
class RewardController(
    private val rewardService: RewardService,
) {

    @PostMapping("/bulk")
    fun createReward(@Valid @RequestBody dto: RewardBulkCommand): ResponseEntity<PageResponse<RewardResponse>> {
        val result = rewardService.createBulk(dto.rewards)
        return ResponseEntity.ok(result)
    }
}