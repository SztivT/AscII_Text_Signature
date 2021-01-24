package signature

import java.io.File
import java.util.*

//3D Array to store the english alphabet (index in 0..25) && space is at [26] && separator is at [27]
val scanner = Scanner(System.`in`)

fun main() {

    print("Enter name and surname: ")
    val input = scanner.nextLine()
    print("Enter person's status: ")
    val note = scanner.nextLine()
    body(input, note)
}

fun headerMaker(length: Int) {
    repeat(length + 8) {
        print("8")
    }
    print("\n")
}

fun leftBorder(addedSpace: Int) {
    print("88  ")
    repeat(addedSpace / 2) {
        print(" ")
    }
}

fun rightBorder(addedSpace: Int) {
    repeat(addedSpace / 2 + addedSpace % 2) {
        print(" ")
    }
    print("  88\n")
}

fun findRoman(c: Char): Array<String> {
    val roman = Scanner(File("ASCII Text Signature\\task\\src\\signature\\roman.txt"))
    val column = roman.nextInt() + 2
    var retArr = arrayOf("")
    if (c.toInt() == 32) {
        retArr[0] = "32"
        retArr += "10"
        return retArr
    }
    while (roman.hasNextLine()) {
        val thisLine = roman.nextLine()
        if (thisLine.first() == c && thisLine[1].toInt() == 32) {
            retArr[0] = thisLine.first().toString()
            retArr += thisLine.substring(2)
            for (i in 2 until column) {

                retArr += roman.nextLine()
            }
            break
        }
    }
    return retArr
}

fun findMedium(c: Char): Array<String> {
    val medium = Scanner(File("ASCII Text Signature\\task\\src\\signature\\medium.txt"))
    val column = medium.nextInt() + 2
    var retArr = arrayOf("")
    if (c.toInt() == 32) {
        retArr[0] = "32"
        retArr += "5"
        return retArr
    }
    while (medium.hasNextLine()) {
        val thisLine = medium.nextLine()
        if (thisLine.first() == c) {
            retArr[0] = thisLine.first().toString()
            retArr += thisLine.substring(2)
            for (i in 2 until column) {

                retArr += medium.nextLine()
            }
            break
        }
    }
    return retArr
}

fun body(input: String, note: String) {
    val romanString = mutableListOf<Array<String>>()
    val mediumString = mutableListOf<Array<String>>()
    var romanLength = 0
    var mediumLength = 0
    for (y in input) {
        romanString.add(findRoman(y))
    }
    for (y in note) {
        mediumString.add(findMedium(y))
    }
    for (letter in romanString) {
        romanLength += letter[1].toInt()
    }
    for (letter in mediumString) {
        mediumLength += letter[1].toInt()
    }
    val fullLength = if (romanLength > mediumLength) romanLength else mediumLength
    headerMaker(fullLength)
    for (i in 2 until romanString[0].size) {
        leftBorder(fullLength - romanLength)
        for (letter in romanString) {
            if (letter[0] == "32") {
                repeat(letter[1].toInt()) {
                    print(" ")
                }
            } else {
                print(letter[i])
            }
        }
        rightBorder(fullLength - romanLength)
    }
    for (i in 2 until mediumString[0].size) {
        leftBorder(fullLength - mediumLength)
        for (letter in mediumString) {
            if (letter[0] == "32") {
                repeat(letter[1].toInt()) {
                    print(" ")
                }
            } else {
                print(letter[i])
            }
        }
        rightBorder(fullLength - mediumLength)
    }
    headerMaker(fullLength)
}
