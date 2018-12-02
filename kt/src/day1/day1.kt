package day1

import java.io.File

fun main(args: Array<String>) {
    val inputStream = File("./src/day1/" + args[0]).inputStream()
    val lineList = mutableListOf<Int>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) }}

    part1(lineList)
    part2(lineList)
}

fun part1(lineList: List<Int>){
    println(lineList.sum())
}

fun part2 (lineList: List<Int>){
    var sum = 0
    val sums = mutableSetOf<Int>()

    while(true) {
        lineList.forEach {
            sum += it
            if (sums.contains(sum)) {
                println(sum)
                return
            }
            sums.add(sum)
        }
    }
}