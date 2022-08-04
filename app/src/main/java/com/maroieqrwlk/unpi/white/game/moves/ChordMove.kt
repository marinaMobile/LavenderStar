package com.maroieqrwlk.unpi.white.game.moves

import com.maroieqrwlk.unpi.white.forEachEightNeighbor
import com.maroieqrwlk.unpi.white.game.Board

class ChordMove(val row: Int, val column: Int) : Move {
    override fun execute(board: Board, changeSet: Board.ChangeSet) {
        if (!board.isRevealed(row, column)) return
        board.forEachEightNeighbor(row, column) { row, column ->
            if (!board.isFlagged(row, column)) changeSet.reveal(row, column)
        }
    }
}
