package com.maroieqrwlk.unpi.white.game.moves

import com.maroieqrwlk.unpi.white.game.Board

interface Move {
    enum class Type {
        Reveal,
        Flag,
        RemoveFlag
    }

    fun execute(board: Board, changeSet: Board.ChangeSet)
}
