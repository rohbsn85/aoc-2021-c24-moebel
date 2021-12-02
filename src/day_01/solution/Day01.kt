package day_01.solution

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("day_01/data/Day01_rob").map {
        it.toInt()
    }

    // part one

    var forEachCounter = 0
    forEachCounter = solutionForEachIndex(input, forEachCounter)
    print("forEach: $forEachCounter")


    val foldCounter = solutionFold(input)
    print("\n")
    print("fold: ${foldCounter.sum}")

    val prevValue = Int.MAX_VALUE
    val count = solutionCount(input, prevValue)

    print("\n")
    print(count)


    // part two

    print("\n")

    val partTwoFoldCounter = solutionFoldPartTwo(input)
    val partTwoFoldCounterAlternate = solutionFoldPartTwoWithoutIndices(input)

    println("solutions for partTwoCount: $partTwoFoldCounter, $partTwoFoldCounterAlternate ")


}

private fun solutionCount(input: List<Int>, prevValue: Int): Int {
    var prevValue1 = prevValue
    return input.count {
        val value = it > prevValue1
        prevValue1 = it
        value
    }
}

private fun solutionFold(input: List<Int>) = input.fold(Day01(input[0], 0)) { acc, value ->
    acc.copy(previousValue = value, sum = if (value > acc.previousValue) acc.sum + 1 else acc.sum)
}

private fun solutionFoldPartTwo(input: List<Int>) =
    input.foldIndexed(Day01_02((input[0] + input[1] + input[2]), 0)) { index, acc, value ->
        if (index in 0..input.size - 3) {
            val windowSum = input.subList(index, index + 3).sum()
            acc.copy(previousSum = windowSum, sum = if (windowSum > acc.previousSum) acc.sum + 1 else acc.sum)
        } else {
            acc
        }
    }

private fun solutionFoldPartTwoWithoutIndices(input: List<Int>) = input.fold(
    Day01_03(
        input[1],
        0,
        input[2],
        input[0] + input[1] + input[2],
        0
    )
) { acc, value ->
    val sum = acc.previousValue + acc.value + value
    val previousSum = acc.sum
    val accValue = value
    val previousVal = acc.value
    acc.copy(
        previousValue = previousVal,
        previousSum = previousSum,
        value = value,
        sum = sum,
        count = if (previousSum < sum) acc.count + 1 else acc.count
    )
}

private fun solutionForEachIndex(input: List<Int>, forEachCounter: Int): Int {
    var forEachCounter1 = forEachCounter
    input.forEachIndexed { index, _ ->
        if (index != 0) {
            if (input[index] > input[index - 1]) {
                forEachCounter1++
            }
        }
    }
    return forEachCounter1
}

data class Day01(val previousValue: Int, val sum: Int)

data class Day01_02(val previousSum: Int, val sum: Int)

data class Day01_03(val previousValue: Int, val previousSum: Int, val value: Int, val sum: Int, val count: Int)