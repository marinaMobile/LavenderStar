package com.maroieqrwlk.unpi.white.game.generators

import com.maroieqrwlk.unpi.white.game.Field

interface FieldGenerator {
    fun generate(rows: Int, columns: Int, args: FieldGenerationArguments): Field
}

data class FieldGenerationArguments(
    val mines: Int
)
