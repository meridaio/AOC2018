package day5

import scala.io.Source.fromFile

object day5 {
  def main(args: Array[String]): Unit ={
    implicit lazy val lines: String = fromFile(args(0)).mkString
    println(part1)
    println(part2)
  }

  def part1(implicit input: String): Int={
    val arr = input.filter(_ >= ' ').toBuffer
    var i = 0
    do {
      if (arr(i) == (if(arr(i+1).isLower) arr(i+1).toUpper else arr(i+1).toLower)){
        arr.remove(i, 2)
        if (i != 0)
          i = i - 1
      }
      else
        i = i + 1
    } while(i+1 < arr.length)
    arr.length
  }

  def part2(implicit input: String): Int = {
    val arr = input.filter(_ >= ' ').toBuffer
    ('A' to 'Z')
      .map(letter => arr.filter(c => c != letter && c != letter.toLower))
      .map(buffer => part1(buffer.mkString("")))
      .min
  }
}
