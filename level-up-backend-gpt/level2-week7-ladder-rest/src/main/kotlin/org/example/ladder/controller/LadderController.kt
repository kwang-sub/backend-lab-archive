package org.example.ladder.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.example.ladder.dto.request.LaderCommand
import org.example.ladder.dto.response.LadderResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.service.LadderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "사다리 게임 API", description = "사다리 게임 관련 API 입니다.")
@RestController
@RequestMapping("/api/v1/ladders")
class LadderController(
    private val ladderService: LadderService,
) {

    @PostMapping
    fun createLadder(@RequestBody @Valid command: LaderCommand): ResponseEntity<LadderResponse> {
        val result = ladderService.createLadder(command.lineCount)
        return ResponseEntity.ok(result)
    }

    @GetMapping
    fun getLadder(): ResponseEntity<LadderResponse> {
        return ResponseEntity.ok(ladderService.getLadder())
    }

    @GetMapping("/play")
    fun getResult(@RequestParam name: String): ResponseEntity<RewardResponse> {
        return ResponseEntity.ok(ladderService.getResult(name))
    }
}


