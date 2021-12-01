package day_01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:


    val input = readInput("day_01/Day01").map {
        it.toInt()
    }
    var forEachCounter = 0
    input.forEachIndexed { index, _ ->
        if (index != 0) {
            if (input[index] > input[index - 1]) {
                forEachCounter++
            }
        }
    }
    print("forEach: $forEachCounter")


    val foldCounter = input.fold(Day01(input[0], 0)) { acc, value ->
        acc.copy(previousValue = value, sum = if (value > acc.previousValue) acc.sum + 1 else acc.sum)
    }
    print("\n")
    print("fold: ${foldCounter.sum}")

    var prevValue = Int.MAX_VALUE
    val count =
        input.count {
            val value = it > prevValue
            prevValue = it
            value
        }

    print("\n")
    print(count)
}

data class Day01(val previousValue: Int, val sum: Int)
