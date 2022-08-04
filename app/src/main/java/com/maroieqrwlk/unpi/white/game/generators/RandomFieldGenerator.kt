package com.maroieqrwlk.unpi.white.game.generators

import com.maroieqrwlk.unpi.white.game.Field

class RandomFieldGenerator : FieldGenerator {
    override fun generate(rows: Int, columns: Int, args: FieldGenerationArguments): Field {
        val field = Field(rows, columns)

        val numbers = (0 until rows * columns).shuffled().take(args.mines)
        for (index in numbers) {
            field[index / columns, index % columns] = true
        }

        return field
    }
}
