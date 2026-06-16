package org.example.ladder.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.example.ladder.dto.request.PlayerBulkCommand
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.PlayerResponse
import org.example.ladder.service.PlayerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "플레이어 API", description = "플레이어 관련 API 입니다.")
@RestController
@RequestMapping("/api/v1/players")
class PlayerController(
    private val playerService: PlayerService, // 플레이어 서비스는 추후 구현 예정
) {

    // TODO : 플레이어 생성 로직 구현 및 스웨거 포맷 정리
    @Operation(summary = "플레이어 생성", description = "플레이어 생성 요청을 처리합니다.")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "플레이어 생성 성공"
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "잘못된 요청"
            )
        ]
    )
    @PostMapping("/bulk")
    fun createBulk(
        @Valid @RequestBody dto: PlayerBulkCommand
    ): ResponseEntity<PageResponse<PlayerResponse>> {
        val result = playerService.createBulk(dto.players)
        return ResponseEntity.ok(result)
    }

}