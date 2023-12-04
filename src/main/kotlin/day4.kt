import java.io.File

fun main() {
    val lineList = mutableListOf<String>()
    File("src/main/resources/day4.txt").useLines { lines -> lines.forEach { lineList.add(it) } }

    val scratchMap = hashMapOf<Int, Int>()

    val cardMap = hashMapOf<Int, String>()
    lineList.forEach {
        val split = it.split(":")
        val split2 = split[0].split(" ")
        val cardNumber = split2[split2.size - 1].toInt()
        cardMap[cardNumber] = split[1]
        scratchMap[cardNumber] = 1
    }

    val cardPoints = mutableListOf<Int>()
    cardMap.forEach { card ->
        val split = card.value.split("|")
        val winNumbers = split[0].split(" ").toMutableList()
        val myNumbers = split[1].split(" ").toMutableList()
        winNumbers.removeIf { it.toIntOrNull() == null }
        myNumbers.removeIf { it.toIntOrNull() == null }
        val commonNumbers = winNumbers.intersect(myNumbers.toSet())
        if (commonNumbers.isNotEmpty()) {
            var points = 1
            val cards = scratchMap[card.key]!!
            var i = 1
            commonNumbers.forEach { value ->
                if (value.toInt() != commonNumbers.last().toInt()) {
                    points *= 2
                }
                repeat(cards) {
                    scratchMap[card.key + i] = scratchMap[card.key + i]!! + 1
                }
                i++
            }
            cardPoints.add(points)
        }

    }

    println("[Part 1 Solution] Sum of all card points: ${cardPoints.sum()}")
    println("[Part 2 Solution] Sum of all scratch cards: ${scratchMap.values.sum()}")

}