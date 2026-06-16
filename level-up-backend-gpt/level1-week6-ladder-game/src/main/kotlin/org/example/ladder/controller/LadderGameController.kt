package org.example.ladder.controller

import org.example.ladder.domain.Ladder
import org.example.ladder.domain.Player
import org.example.ladder.domain.Reword
import org.example.ladder.factory.DomainFactory
import org.example.ladder.service.LadderGameService
import org.example.ladder.view.InputView

class LadderGameController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val ladderGameService: LadderGameService,
) {
    fun startGame() {
        val players = inputView.inputPlayerNames()
            .map { DomainFactory.createPlayer(it) }
        val rewords = inputView.inputRewords(players.size)
            .map { DomainFactory.createReword(it) }

        val layerCount = inputView.inputLayerCount()
        val ladder = DomainFactory.createLadder(layerCount, players.size - 1)
        outputView.printLadder(players, rewords, ladder)

        var exit: Boolean
        do {
            val targetPlayerName = inputView.inputTargetPlayer(players)

            val reword = ladderGameService.playGame(ladder, players, rewords, targetPlayerName)
            outputView.printReword(targetPlayerName, reword)

            exit = inputView.inputExit()
        } while (!exit)

    }
}

class OutputView {
    fun printLadder(players: List<Player>, rewords: List<Reword>, ladder: Ladder) {
        players.forEach { player -> print(String.format("%-6s", player.name)) }
        println()
        ladder.lines.forEach {
            print("|")
            it.points.forEach { point ->
                if (point.isRightConnect) {
                    print("-----")
                } else {
                    print("     ")
                }
                print("|")
            }
            println()
        }
        rewords.forEach { reword -> print(String.format("%-6s", reword.name)) }
        println()
    }

    fun printReword(targetPlayerName: String, reword: Reword) {
        println("${targetPlayerName}의 보상은 ${reword.name}입니다.")
    }
}
