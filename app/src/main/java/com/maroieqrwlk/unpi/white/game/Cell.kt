package com.maroieqrwlk.unpi.white.game

import com.maroieqrwlk.unpi.white.game.Board

data class Cell(
    val row: Int,
    val column: Int,
    private val board: Board
) {
    val isRevealed get() = board.isRevealed(row, column)
    val isFlagged get() = board.isFlagged(row, column)
    val isMine get() = board.isMine(row, column)

    val adjacentMines get() = board.getAdjacentMines(row, column)
}
