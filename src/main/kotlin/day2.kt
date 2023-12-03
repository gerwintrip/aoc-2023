import java.io.File

fun main() {
    val lineList = mutableListOf<String>()
    File("src/main/resources/day2.txt").useLines { lines -> lines.forEach { lineList.add(it)} }

    val gameMap = hashMapOf<Int, String>()
    lineList.forEach {
        val split = it.split(":")
        val gameNumber = split[0].split(" ")[1].toInt()
        gameMap[gameNumber] = split[1]
    }

    val possibleGames = calculatePossibleGames(gameMap)
    println("[Part 1 Solution] Sum of all possible game ids: ${possibleGames.sum()}")

    val gamesPower = calculateGamesPower(gameMap)
    println("[Part 2 Solution] Sum of all possible game ids: ${gamesPower.sum()}")
}

fun calculatePossibleGames(gameMap: HashMap<Int, String>): MutableList<Int> {
    val possibleGames = mutableListOf<Int>()
    val maxPerColor = hashMapOf("red" to 12, "green" to 13, "blue" to 14)
    gameMap.forEach gameLoop@{ game ->
        val games = game.value.split(";")
        games.forEach { colors ->
            val splittedColors = colors.split(",")
            splittedColors.forEach { color ->
                val colorSplit = color.trim().split(" ")
                val colorName = colorSplit[1]
                val colorCount = colorSplit[0].toInt()
                if (colorCount > maxPerColor[colorName]!!) {
                    return@gameLoop
                }
            }
        }
        possibleGames.add(game.key)
    }
    return possibleGames
}

fun calculateGamesPower(gameMap: HashMap<Int, String>): MutableList<Int> {
    val gamesPower = mutableListOf<Int>()
    gameMap.forEach gameLoop@{ game ->
        val games = game.value.split(";")
        val gamePower = hashMapOf<String, Int>()
        games.forEach { colors ->
            val splittedColors = colors.split(",")
            splittedColors.forEach { color ->
                val colorSplit = color.trim().split(" ")
                val colorName = colorSplit[1]
                val colorCount = colorSplit[0].toInt()
                if (gamePower[colorName] == null || gamePower[colorName]!! < colorCount) {
                    gamePower[colorName] = colorCount
                }
            }
        }
        var power = 1
        gamePower.forEach { (_, number) -> power *= number }
        gamesPower.add(power)
    }
    return gamesPower
}
