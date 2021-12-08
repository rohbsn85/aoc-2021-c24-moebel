package day_02.solution

import readInput

fun main() {

    val input = readInput("day_02/data/Day02_rob").mapNotNull { line ->
        val splitted = line.split(" ")

        if (splitted.size == 2) {
            val direction = Direction.fromKey(splitted[0])
            val distance = splitted[1].toInt()

            direction?.let {
                Command(it, distance)
            }
        } else {
            null
        }
    } // [Command(forward, 9), .... ]

    val position = input.fold(SubmarinePosition()) { currentPosition, command ->
        command.direction.driveAction(currentPosition, command.distance)
    }

    print(position.multiplied())
}

enum class Direction(val key: String, val driveAction: (SubmarinePosition, Int) -> SubmarinePosition) {
    FORWARD("forward", { currentPosition, distance ->
        currentPosition.copy(
            horizontal = currentPosition.horizontal + distance,
            depth = currentPosition.depth + currentPosition.aim * distance,
        )
    }),
    DOWN("down", { currentPosition, distance ->
        currentPosition.copy(
            aim = currentPosition.aim + distance
        )
    }),
    UP("up", { currentPosition, distance ->
        currentPosition.copy(
            aim = currentPosition.aim - distance
        )
    }),
//    BACKWARDS("backwards"),
    ;

    companion object {
        fun fromKey(key: String) = values().firstOrNull { it.key == key }
    }
}

data class Command(
    val direction: Direction,
    val distance: Int,
)

data class SubmarinePosition(
    var horizontal: Int = 0,
    var depth: Int = 0,
    var aim: Int = 0,
) {
    fun multiplied() = horizontal * depth
}