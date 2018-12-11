package day4

import scala.io.Source.fromFile
import scala.language.implicitConversions

object day4 {
  def main(args: Array[String]): Unit ={
    implicit lazy val lines: List[String] = fromFile(args(0)).getLines.toList
    println(part1)
  }

  private val timeregex = """\[\d+\-\d+\-\d+\ \d+\:(\d{2})\]""".r

  def part1(implicit lines: List[String]): Int ={
    val sorted = lines.sorted
    var list: List[String] = Nil
    sorted.foreach(f => {
      val timeregex(time) = f
      time :: list
    })

    1
  }
}
