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
    var index = 0
    var newLine = line
    while (index < newLine.length) {
        if (newLine[index].code in 48..57) {
            index++
            continue
        }
        if (index + 3 <= newLine.length && newLine.substring(index, index + 3) == "one") {
            newLine = newLine.replaceRange(index, index + 3, "o1e")
            index++
        } else if (index + 3 <= newLine.length && newLine.substring(index, index + 3) == "two") {
            newLine = newLine.replaceRange(index, index + 3, "2o")
            index++
        } else if (index + 5 <= newLine.length && newLine.substring(index, index + 5) == "three") {
            newLine = newLine.replaceRange(index, index + 5, "t3e")
            index++
        } else if (index + 4 <= newLine.length && newLine.substring(index, index + 4) == "four") {
            newLine = newLine.replaceRange(index, index + 4, "4")
            index++
        } else if (index + 4 <= newLine.length && newLine.substring(index, index + 4) == "five") {
            newLine = newLine.replaceRange(index, index + 4, "5e")
            index++
        } else if (index + 3 <= newLine.length && newLine.substring(index, index + 3) == "six") {
            newLine = newLine.replaceRange(index, index + 3, "6")
            index++
        } else if (index + 5 <= newLine.length && newLine.substring(index, index + 5) == "seven") {
            newLine = newLine.replaceRange(index, index + 5, "7n")
            index++
        } else if (index + 5 <= newLine.length && newLine.substring(index, index + 5) == "eight") {
            newLine = newLine.replaceRange(index, index + 5, "e8t")
            index++
        } else if (index + 4 <= newLine.length && newLine.substring(index, index + 4) == "nine") {
            newLine = newLine.replaceRange(index, index + 4, "n9e")
            index++
        } else {
            index++
        }
    }
    return newLine
}
