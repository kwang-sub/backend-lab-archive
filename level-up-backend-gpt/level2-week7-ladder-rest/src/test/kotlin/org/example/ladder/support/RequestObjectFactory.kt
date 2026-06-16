package org.example.ladder.support

import org.example.ladder.dto.request.PlayerBulkCommand
import org.example.ladder.dto.request.PlayerCommand
import org.example.ladder.dto.request.RewardBulkCommand
import org.example.ladder.dto.request.RewardCommand

class RequestObjectFactory {

    companion object {
        fun createRewardReqDto(name: String): RewardCommand {
            return RewardCommand(name = name)
        }

        fun createRewardBulkReqDto(nameList: Collection<String>): RewardBulkCommand {
            val rewardReqDtos = nameList.map { createRewardReqDto(it) }
            return RewardBulkCommand(rewardReqDtos)
        }


        fun createPlayerReqDto(name: String): PlayerCommand {
            return PlayerCommand(name = name)
        }

        fun createPlayerBulkReqDto(nameList: List<String>): PlayerBulkCommand {
            val playerReqDtos = nameList.map { createPlayerReqDto(it) }
            return PlayerBulkCommand(playerReqDtos)
        }
    }
}