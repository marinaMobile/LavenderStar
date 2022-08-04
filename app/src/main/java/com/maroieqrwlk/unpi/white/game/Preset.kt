package com.maroieqrwlk.unpi.white.game

import com.maroieqrwlk.unpi.white.game.generators.FieldGenerationArguments
import com.maroieqrwlk.unpi.white.game.generators.FieldGenerator
import com.maroieqrwlk.unpi.white.game.generators.RandomFieldGenerator

enum class Preset(val rows: Int, val columns: Int, val mines: Int) {
    EASY(9, 9, 10),
    MEDIUM(16, 16, 40),
    HARD(30, 16, 99);

    fun generate(generator: FieldGenerator = RandomFieldGenerator()): Field {
        return generator.generate(rows, columns, FieldGenerationArguments(mines))
    }


    companion object{
        fun of(id: String?): Preset {
            if(id == null)return EASY
            for(value in values()){
                if(value.name == id) return value
            }
            return EASY
        }
    }
}
