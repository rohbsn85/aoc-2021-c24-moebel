package day_03.solution

import readInput

fun main() {

    val input = readInput("day_03/data/Day03_rob").map { bitSring ->
        bitSring.chunked(1).map { bit -> BitChar.from(bit) }
    }
    val counters: MutableList<Int> = MutableList(input[0].size) { 0 }

    input.forEachIndexed { index, bitChars ->
        bitChars.forEachIndexed { index2, bitChar ->
            counters[index2] = counters[index2] + bitChar.value
        }
    }

    val gamma = counters.map {
        if (it >= input.size / 2) BitChar.ONE else BitChar.ZERO
    }.fold("") { acc, bitChar ->
        acc + bitChar.char
    }.toInt(2)

    val epsilon = counters.map {
        if (it >= input.size / 2) BitChar.ZERO else BitChar.ONE
    }.fold("") { acc, bitChar ->
        acc + bitChar.char
    }.toInt(2)


    print(gamma * epsilon)
}

enum class BitChar(val char: String, val value: Int) {
    ONE("1", 1),
    ZERO("0", 0)

    ;

    companion object {
        fun from(char: String) =
            values().firstOrNull { it.char == char } ?: ZERO
    }
}