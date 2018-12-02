package day2

import java.io.File

fun main(args: Array<String>){
    val inputStream = File("./src/day2/" + args[0]).inputStream()
    val lineList = mutableListOf<CharArray>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toCharArray()) }}

    part1(lineList)
    part2(lineList)
}

fun part1(lineList: List<CharArray>){
    var twocount = 0
    var threecount = 0
    lineList.forEach{
        val hashMap = mutableMapOf<Char, Int>()
        it.map { char -> hashMap.put(char, hashMap.getOrDefault(char, 0) + 1)}
        if (hashMap.containsValue(2))
            twocount++
        if (hashMap.containsValue(3))
            threecount++
    }
    println("Checksum: " + (twocount * threecount))
}

fun part2(lineList: List<CharArray>){
    val bigset = mutableSetOf<String>()
    lineList.forEach{
        val tmp = mutableSetOf<String>()
        for (i in 0 until it.size){
            val sub = (it.slice(0 until i) + it.slice(i+1 until it.size)).joinToString(separator = ""){ f -> f.toString() }
            tmp.add(sub)
            if(bigset.contains(sub)){
                println("i: ${lineList.indexOf(it)}, sub: $sub")
                return
            }
        }
        bigset.addAll(tmp)
    }
    println("Error")
}