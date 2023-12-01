import java.io.File

fun main() {
    val lineList = mutableListOf<String>()
    File("src/main/resources/day1.txt").useLines { lines -> lines.forEach { lineList.add(it)} }

    var sum = 0
    var sum2 = 0

    lineList.forEach {
        sum += firstAndLastNumbers(it)
        sum2 += firstAndLastNumbers(replaceNumberStringsWithIntegers(it))
    }

    println("[Part 1 Solution] Sum of all numbers: $sum")
    println("[Part 2 Solution] Sum of all numbers: $sum2")
}

fun firstAndLastNumbers(line: String): Int {
    val lineNumbers = mutableListOf<Int>()
    for (i in line) {
        if (i.code in 48..57) {
            lineNumbers.add(i.code - 48)
            break
        }
    }
    val reversed = line.reversed()
    for (i in reversed) {
        if (i.code in 48..57) {
            lineNumbers.add(i.code - 48)
            break
        }
    }
    return lineNumbers[0] * 10 + lineNumbers[1]
}

fun replaceNumberStringsWithIntegers(line: String): String {
    val numberMap = mapOf(
        "one" to "o1e", "two" to "t2o", "three" to "t3e",
        "four" to "4", "five" to "5e", "six" to "6",
        "seven" to "7n", "eight" to "e8t", "nine" to "9e"
    )
    var resultLine = line
    numberMap.forEach {(key, value) ->
        resultLine = resultLine.replace(key, value)
    }
    return resultLine
}
